package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeSkillDTO {
    @NotNull(message = MessageConstants.SKILLS_CAN_NOT_BE_NULL)
    List<String> skills;
}
