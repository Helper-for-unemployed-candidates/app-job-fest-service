package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.AgeRange;
import com.jobhunter.appjobfestservice.entity.enums.Experience;
import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Requirement extends AbsUUIDEntity {
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Skills> skills;
    private AgeRange ageFrom; // range

    private Experience experience;
}
