package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeAboutDTO {
    @NotBlank(message = MessageConstants.ABOUT_CAN_NOT_BE_BLANK)
    private String about;
}
