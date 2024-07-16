package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class JobType extends AbsUUIDEntity {
    private String name;
    private Category category;
}
