package com.jobhunter.appjobfestservice.websocket_chat_system.controllers;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.UserDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.User;
import com.jobhunter.appjobfestservice.websocket_chat_system.mappers.UserMapper;
import com.jobhunter.appjobfestservice.websocket_chat_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload UserDTO user) {
        userService.saveUser(user);
        return userMapper.dtotoUser(user);
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnectUser(@Payload UserDTO user) {
        userService.disconnect(user);
        return userMapper.dtotoUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
