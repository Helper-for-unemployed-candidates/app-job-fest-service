package com.jobhunter.appjobfestservice.websocket_chat_system.service;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.UserDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.enums.Status;
import com.jobhunter.appjobfestservice.websocket_chat_system.mappers.UserMapper;
import com.jobhunter.appjobfestservice.websocket_chat_system.repositories.UserRepository;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(UserDTO dto) {
        log.info("Saving user: {}", dto);
        User user = userMapper.dtotoUser(dto);
        user.setStatus(Status.ONLINE);
        log.info("Saved user: {}", user);
        userRepository.save(user);
    }

    @Override
    public void disconnect(UserDTO dto) {
        var savedUser = userRepository
                .findById(
                        dto.nickName())
                .orElseThrow(
                        () -> new RuntimeException
                                ("User not found with nickName: " + dto.nickName()));

        if (savedUser != null) {
            savedUser.setStatus(Status.OFFLINE);
            userRepository.save(savedUser);
        }
    }

    @Override
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
