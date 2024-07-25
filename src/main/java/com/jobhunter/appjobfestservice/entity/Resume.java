package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.ResumeStatus;
import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class Resume extends AbsStringEntity {

    private String about;

    @DocumentReference(lookup = "{ 'resume': ?#{#self._id} }", lazy = true)
    private List<Experience> experience;

    @DocumentReference(lookup = "{ 'resume': ?#{#self._id} }", lazy = true)
    private List<Education> education;

    private List<String> skills;

    @DocumentReference(lookup = "{ 'resume': ?#{#self._id} }", lazy = true)
    private List<Language> language;

    private ResumeStatus status;
}
