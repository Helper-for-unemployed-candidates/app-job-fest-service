package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class JobType extends AbsUUIDEntity {
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
