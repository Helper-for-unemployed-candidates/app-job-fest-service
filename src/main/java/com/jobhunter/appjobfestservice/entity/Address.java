package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address extends AbsUUIDEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    private City city;
    private String fullAddress;

}
