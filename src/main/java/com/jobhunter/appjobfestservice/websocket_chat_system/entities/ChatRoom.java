package com.jobhunter.appjobfestservice.websocket_chat_system.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatRoom {
    @Id
    private String id;

    private String chatId;
    private String senderId;
    private String recipientId;

}