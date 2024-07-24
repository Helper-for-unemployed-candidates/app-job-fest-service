package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.JobApplication;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private String text;
    private byte rate;
}
