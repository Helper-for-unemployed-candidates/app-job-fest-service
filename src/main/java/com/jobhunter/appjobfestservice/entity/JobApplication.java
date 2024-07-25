package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.Status;
import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class JobApplication extends AbsStringEntity {
    @DBRef
    private JobType jobType;

    private String description;

    @DBRef
    private Requirement requirements;
    private Status status;
    @DBRef
    private Address address;
    private String title;
}
