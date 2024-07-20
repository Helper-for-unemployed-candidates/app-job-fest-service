package com.jobhunter.appjobfestservice.websocket_chat_system.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;

}
