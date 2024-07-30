package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.ChatStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDTO {
    private String chatId;
    private String jobApplicationId;
    private String resumeId;
    private ChatStatus status;
}
