package com.jobhunter.appjobfestservice.websocket_chat_system.mappers;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.ChatMessageDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage);

    ChatMessage toChatMessage(ChatMessageDTO chatMessageDTO);

}
