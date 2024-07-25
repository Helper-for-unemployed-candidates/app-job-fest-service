package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Experience extends AbsStringEntity {

    private String companyName;

    private Date fromDate;

    private Date toDate;

    private String position;

    private String description;

    @DocumentReference(lazy = true)
    private Resume resume;
}
