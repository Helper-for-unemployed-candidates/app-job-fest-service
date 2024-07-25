package com.jobhunter.appjobfestservice.entity.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbsAuditingEntity extends AbsDateAuditingEntity {

    @CreatedBy
    private UUID createdById;

    @LastModifiedBy
    private UUID updatedById;
}
