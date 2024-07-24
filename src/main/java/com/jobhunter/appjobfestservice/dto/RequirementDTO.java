package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.Requirement;
import com.jobhunter.appjobfestservice.entity.Skills;
import com.jobhunter.appjobfestservice.entity.enums.AgeRange;
import com.jobhunter.appjobfestservice.entity.enums.Experience;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementDTO  {
    private List<SkillsDTO> skills;
    private AgeRange ageFrom; // range
    private Experience experience;
}
