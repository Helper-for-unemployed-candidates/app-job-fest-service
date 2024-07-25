package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.enums.TypeOfEducation;
import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Education extends AbsStringEntity {

    private TypeOfEducation type;

    private String name;

    @DocumentReference
    private Specialization specialization;

    private Date fromDate;

    private Date toDate;

    @DocumentReference(lazy = true)
    private Resume resume;

}
