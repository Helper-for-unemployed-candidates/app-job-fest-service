package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.JobApplication;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    @NotBlank(message = "text cannot be blank")
    private String text;

    private byte rate;
}
