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
public class Category extends AbsUUIDEntity {
    private String name;
}
