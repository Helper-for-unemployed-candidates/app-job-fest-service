package com.jobhunter.appjobfestservice.websocket_chat_system.dtos;


import com.jobhunter.appjobfestservice.websocket_chat_system.enums.Status;
import lombok.*;

@Builder
public record UserDTO(String nickName,String fullName, Status status) {

}
