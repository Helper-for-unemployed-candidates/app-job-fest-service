package com.jobhunter.appjobfestservice.websocket_chat_system.service;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.ChatMessageDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatMessage;
import com.jobhunter.appjobfestservice.websocket_chat_system.exceptions.ChatRoomNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChatMessageService {

    ChatMessage save(ChatMessageDTO chatMessage) throws ChatRoomNotFoundException;

    List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
