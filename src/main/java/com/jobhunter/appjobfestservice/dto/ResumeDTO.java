package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.ResumeStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeDTO {
    private String id;
    private String about;
    private List<ExperienceDTO> experience;
    private List<EducationDTO> education;
    private List<String> skills;
    private List<LanguageDTO> language;
    private ResumeStatus status;
}
