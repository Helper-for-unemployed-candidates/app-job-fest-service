package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.MessageDTO;
import com.jobhunter.appjobfestservice.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MessageMapper {
    MessageDTO toMessageDTO(Message message);

    List<MessageDTO> toMessageDTOList(List<Message> messages);
}
