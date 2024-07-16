package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat extends AbsUUIDEntity {
    //    private String name;
    private UUID applicantId;
    private UUID companyId;

    private UUID blockerId;
    private UUID blockedId;
    private boolean blocked;
    private LocalDateTime blockedAt;
    private ChatStatus status;


    @ManyToOne
    private JobApplication jobApplication;

}
