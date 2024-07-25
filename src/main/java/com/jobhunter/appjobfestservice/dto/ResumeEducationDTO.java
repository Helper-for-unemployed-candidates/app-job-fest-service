package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeEducationDTO {
    @Valid
    @NotNull(message = MessageConstants.EDUCATION_LIST_CANNOT_BE_NULL)
    List<EducationDTO> educations;
}
