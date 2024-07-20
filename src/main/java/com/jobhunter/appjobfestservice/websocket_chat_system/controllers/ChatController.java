package com.jobhunter.appjobfestservice.websocket_chat_system.controllers;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.ChatMessageDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatMessage;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatNotification;
import com.jobhunter.appjobfestservice.websocket_chat_system.exceptions.ChatRoomNotFoundException;
import com.jobhunter.appjobfestservice.websocket_chat_system.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessageDTO chatMessage
    ) throws ChatRoomNotFoundException {
        ChatMessage saved = chatMessageService.save(chatMessage);
        //john/queue/messages
        template.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                ChatNotification.builder()
                        .chatId(saved.getChatId())
                        .senderId(saved.getSenderId())
                        .recipientId(saved.getRecipientId())
                        .content(saved.getContent())
                        .build()
        );
    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

}
//ADN5RJSLWVW8EMRQWL4K93Z9
