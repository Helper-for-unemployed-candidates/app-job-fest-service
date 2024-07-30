package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.ChatDTO;
import com.jobhunter.appjobfestservice.dto.MessageDTO;
import com.jobhunter.appjobfestservice.dto.PaginationDTO;
import com.jobhunter.appjobfestservice.dto.SendMessageDTO;
import com.jobhunter.appjobfestservice.repositories.projection.ChatProjection;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface ChatService {
    Response<String> createChatRoom(String jobApplicationId, String resumeId);

    void processMessage(SendMessageDTO message);

    Response<PaginationDTO<MessageDTO>> getMessages(String chatRoomId, int page, int size);

    Response<Page<ChatProjection>> getChats(int page, int size, String jobApplicationId);
}
