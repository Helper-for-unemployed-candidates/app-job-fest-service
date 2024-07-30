package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.ChatDTO;
import com.jobhunter.appjobfestservice.dto.MessageDTO;
import com.jobhunter.appjobfestservice.dto.PaginationDTO;
import com.jobhunter.appjobfestservice.dto.SendMessageDTO;
import com.jobhunter.appjobfestservice.repositories.projection.ChatProjection;
import com.jobhunter.appjobfestservice.service.ChatService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatControllerImpl implements ChatController {

    private final ChatService chatService;

    @Override
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<String> createChatRoom(String jobApplicationId, String resumeId) {
        return chatService.createChatRoom(jobApplicationId, resumeId);
    }

    @Override
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public void processMessage(SendMessageDTO message) {
        chatService.processMessage(message);
    }

    @Override
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<PaginationDTO<MessageDTO>> getMessages(String chatRoomId, int page, int size) {
        return chatService.getMessages(chatRoomId, page, size);
    }

    @Override
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<Page<ChatProjection>> getChats(int page, int size, String jobApplicationId) {
        return chatService.getChats(page, size, jobApplicationId);
    }
}
