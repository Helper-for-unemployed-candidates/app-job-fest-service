package com.jobhunter.appjobfestservice.websocket_chat_system.service;

import com.jobhunter.appjobfestservice.websocket_chat_system.dtos.ChatMessageDTO;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatMessage;
import com.jobhunter.appjobfestservice.websocket_chat_system.exceptions.ChatRoomNotFoundException;
import com.jobhunter.appjobfestservice.websocket_chat_system.mappers.ChatMessageMapper;
import com.jobhunter.appjobfestservice.websocket_chat_system.repositories.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessageDTO dto) throws ChatRoomNotFoundException {

        var chatMessage = chatMessageMapper.toChatMessage(dto);
        String chatId = chatRoomService
                .getChatRoomId
                        (dto.getSenderId(),
                                dto.getRecipientId(),
                                true)
                .orElseThrow(() -> new ChatRoomNotFoundException("chatRoom is not available"));
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }


    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId
                .map(chatMessageRepository::findByChatId)
                .orElse(new ArrayList<>());
    }
}
