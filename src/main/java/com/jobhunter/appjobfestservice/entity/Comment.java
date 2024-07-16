package com.jobhunter.appjobfestservice.entity;


import com.jobhunter.appjobfestservice.entity.template.AbsUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends AbsUUIDEntity {
    private UUID userId;
    private String text;
    private byte rate;

    @ManyToOne
    private JobApplication jobApplication;

}
