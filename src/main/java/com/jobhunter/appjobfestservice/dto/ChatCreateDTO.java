package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.ChatStatus;
import com.jobhunter.appjobfestservice.entity.JobApplication;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChatCreateDTO {
    //    private String name;
    private UUID applicantId;
    private UUID companyId;

    private UUID blockerId;
    private UUID blockedId;
    private boolean blocked;
    private LocalDateTime blockedAt;
    private ChatStatus status;


//    @ManyToOne(cascade = CascadeType.PERSIST)
    private JobApplication jobApplication;
}
