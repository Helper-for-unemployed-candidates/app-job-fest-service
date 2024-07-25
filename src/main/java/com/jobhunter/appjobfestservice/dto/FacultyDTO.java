package com.jobhunter.appjobfestservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FacultyDTO {
    String facultyName;
    List<SpecializationDTO> specializations;
}
