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
public class Message extends AbsUUIDEntity {
    @ManyToOne
    private Chat chat;
    private String content;
    private UUID senderId;
    private UUID recipientId;
    private boolean seen;

}
