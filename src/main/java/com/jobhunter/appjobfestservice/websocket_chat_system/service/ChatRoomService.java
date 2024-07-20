package com.jobhunter.appjobfestservice.websocket_chat_system.service;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ChatRoomService {
    Optional<String> getChatRoomId(String senderId, String receiverId, boolean createRoomIfNotExists);

    String createChatId(String senderId, String receiverId);
}
