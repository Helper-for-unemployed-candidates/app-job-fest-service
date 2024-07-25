package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.AgeRange;
import com.jobhunter.appjobfestservice.entity.enums.ExperienceEnum;
import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Requirement extends AbsStringEntity {
    private List<String> skills;
    private AgeRange ageFrom; // range
    private ExperienceEnum experience;
}
