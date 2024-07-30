package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
public class Chat extends AbsStringEntity {
    private UUID applicantId;
    private UUID companyId;

    private UUID blockerId;
    private UUID blockedId;
    private boolean blocked;
    private LocalDateTime blockedAt;
    private ChatStatus status;

    @DocumentReference(lazy = true)
    private JobApplication jobApplication;

    @DocumentReference(lazy = true)
    private Resume resume;
}
