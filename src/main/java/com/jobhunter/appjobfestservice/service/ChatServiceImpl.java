package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.entity.Chat;
import com.jobhunter.appjobfestservice.entity.JobApplication;
import com.jobhunter.appjobfestservice.entity.Message;
import com.jobhunter.appjobfestservice.entity.Resume;
import com.jobhunter.appjobfestservice.exceptions.RestException;
import com.jobhunter.appjobfestservice.mappers.ChatMapper;
import com.jobhunter.appjobfestservice.mappers.MessageMapper;
import com.jobhunter.appjobfestservice.repositories.ChatRepository;
import com.jobhunter.appjobfestservice.repositories.JobApplicationRepository;
import com.jobhunter.appjobfestservice.repositories.MessageRepository;
import com.jobhunter.appjobfestservice.repositories.ResumeRepository;
import com.jobhunter.appjobfestservice.repositories.projection.ChatProjection;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.payload.UserPrincipal;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import com.jobhunter.appjobfestservice.utils.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final JobApplicationRepository jobApplicationRepository;
    private final ResumeRepository resumeRepository;
    private final SimpMessagingTemplate template;

    @Override
    public Response<String> createChatRoom(String jobApplicationId, String resumeId) {
        if (jobApplicationId == null)
            throw RestException.restThrow(MessageConstants.JOB_APPLICATION_ID_CANNOT_BE_NULL);
        if (resumeId == null)
            throw RestException.restThrow(MessageConstants.RESUME_ID_CANNOT_BE_NULL);
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_JOB_APPLICATION_ID));
        Resume resume = resumeRepository.findByIdAndDeletedFalse(resumeId)
                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_RESUME_ID));
        UserPrincipal user = ConstantFields.currentUser();
        Chat chat = Chat.builder()
                .applicantId(resume.getCreatedById())
                .companyId(jobApplication.getCreatedById())
                .jobApplication(jobApplication)
                .resume(resume)
                .createdById(user.getId())
                .build();
        chatRepository.save(chat);
        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public void processMessage(SendMessageDTO message) {
        Chat chat = chatRepository.findById(message.getChatId())
                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_CHAT_ID));
        UserPrincipal user = ConstantFields.currentUser();
        Message entity = Message.builder()
                .chat(chat)
                .senderId(user.getId())
                .recipientId(Objects.equals(user.getRole(), RoleEnum.COMPANY) ? chat.getApplicantId() : chat.getCompanyId())
                .content(message.getContent())
                .createdById(user.getId())
                .build();
        entity = messageRepository.save(entity);

        template.convertAndSendToUser(
                entity.getRecipientId().toString(),
                "/queue/messages",
                ChatNotificationDTO.builder()
                        .chatId(entity.getChat().getId())
                        .senderId(entity.getSenderId())
                        .recipientId(entity.getRecipientId())
                        .content(entity.getContent())
                        .sendAt(entity.getCreatedAt())
                        .build()
        );
    }

    @Override
    public Response<PaginationDTO<MessageDTO>> getMessages(String chatRoomId, int page, int size) {
        Slice<Message> messages = messageRepository.findByChatIdOrderByCreatedAtDesc(chatRoomId, PageRequest.of(page, size));
        return Response.successResponse(
                PaginationDTO.makeForSlice(page, size,
                        messageMapper.toMessageDTOList(messages.getContent()),
                        messages.hasPrevious(), messages.hasNext()
                )
        );
    }

    @Override
    public Response<Page<ChatProjection>> getChats(int page, int size, String jobApplicationId) {
        UserPrincipal user = ConstantFields.currentUser();
        if (Objects.equals(user.getRole(), RoleEnum.COMPANY) && jobApplicationId == null)
            throw RestException.restThrow(MessageConstants.JOB_APPLICATION_ID_CANNOT_BE_NULL);
        PageRequest request = PageRequest.of(page, size);
        Page<ChatProjection> chats;
        if (Objects.equals(user.getRole(), RoleEnum.APPLICANT)) {
            chats = chatRepository.findByApplicantId(user.getId(), request);
        } else {
            chats = chatRepository.findByCompanyIdAndJobApplication_Id(user.getId(), jobApplicationId, request);
        }
        return Response.successResponse(chats);
    }
}
