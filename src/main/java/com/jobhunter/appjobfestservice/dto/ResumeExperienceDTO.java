package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeExperienceDTO {
    @Valid
    @NotNull(message = MessageConstants.EXPERIENCES_CAN_NOT_BE_NULL)
    List<ExperienceDTO> experiences;
}
