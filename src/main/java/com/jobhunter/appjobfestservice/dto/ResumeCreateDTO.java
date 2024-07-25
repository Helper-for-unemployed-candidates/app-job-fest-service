package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeCreateDTO {
    @NotBlank(message = MessageConstants.ABOUT_CAN_NOT_BE_BLANK)
    private String about;
    @Valid
    private List<ExperienceDTO> experiences;
    @Valid
    private List<EducationDTO> educations;
    private List<String> skills;
    @Valid
    private List<LanguageDTO> languages;
}
