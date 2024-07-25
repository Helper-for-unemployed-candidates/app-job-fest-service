package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.AgeRange;
import com.jobhunter.appjobfestservice.entity.enums.ExperienceEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementDTO  {
    private List<String> skills;
    private AgeRange ageFrom; // range
    private ExperienceEnum experience;
}
