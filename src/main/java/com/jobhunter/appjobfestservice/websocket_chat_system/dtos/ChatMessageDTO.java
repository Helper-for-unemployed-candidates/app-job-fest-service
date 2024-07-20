package com.jobhunter.appjobfestservice.websocket_chat_system.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private Date timestamp;
}
