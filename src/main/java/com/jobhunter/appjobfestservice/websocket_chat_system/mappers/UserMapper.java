package com.jobhunter.appjobfestservice.websocket_chat_system.mappers;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.UserDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToDTO(User user);

    User dtotoUser(UserDTO dto);
}
