package com.jobhunter.appjobfestservice.websocket_chat_system.service;


import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.UserDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    void saveUser(UserDTO dto);

    void disconnect(UserDTO dto);

    List<User> findConnectedUsers();
}
