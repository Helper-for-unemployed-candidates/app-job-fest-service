package com.jobhunter.appjobfestservice.entity;

import com.jobhunter.appjobfestservice.entity.enums.Languages;
import com.jobhunter.appjobfestservice.entity.enums.Level;
import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Language extends AbsStringEntity {

    private Languages languages;

    private Level level;

    @DocumentReference(lazy = true)
    private Resume resume;
}
