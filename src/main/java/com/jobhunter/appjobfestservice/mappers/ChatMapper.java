package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.ChatDTO;
import com.jobhunter.appjobfestservice.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ChatMapper {
    @Mapping(target = "chatId", source = "id")
    @Mapping(target = "resumeId", source = "resume.id")
    @Mapping(target = "jobApplicationId", source = "jobApplication.id")
    ChatDTO toDTO(Chat chat);

    List<ChatDTO> toDTO(List<Chat> chats);
}
