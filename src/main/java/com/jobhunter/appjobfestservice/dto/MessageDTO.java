package com.jobhunter.appjobfestservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    private UUID senderId;
    private UUID recipientId;
    private String content;
    private LocalDateTime createdAt;
}
