package com.jobhunter.appjobfestservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ChatNotificationDTO {
    private String chatId;
    private UUID senderId;
    private UUID recipientId;
    private String content;
    private LocalDateTime sendAt;
}
