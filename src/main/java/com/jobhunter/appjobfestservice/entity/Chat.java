package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsStringEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Chat extends AbsStringEntity {
    //    private String name;
    private UUID applicantId;
    private UUID companyId;

    private UUID blockerId;
    private UUID blockedId;
    private boolean blocked;
    private LocalDateTime blockedAt;
    private ChatStatus status;

    @DBRef
    private JobApplication jobApplication;

}
