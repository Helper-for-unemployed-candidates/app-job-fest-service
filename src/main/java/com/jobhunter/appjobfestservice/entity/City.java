package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.enums.Region;
import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class City extends AbsUUIDEntity {
    private String cityName;
    private Region region;


}
