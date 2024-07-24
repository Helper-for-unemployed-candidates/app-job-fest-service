package com.jobhunter.appjobfestservice.websocket_chat_system.entities;


import com.jobhunter.appjobfestservice.websocket_chat_system.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
