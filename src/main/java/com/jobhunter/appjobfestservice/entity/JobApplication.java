package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.Status;
import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_app")
public class JobApplication extends AbsUUIDEntity {
    private UUID userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private JobType jobType;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Requirement requirements;
    private Status status;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    private String title;
}
