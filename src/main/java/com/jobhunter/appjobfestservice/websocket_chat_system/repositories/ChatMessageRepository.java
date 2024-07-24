package com.jobhunter.appjobfestservice.websocket_chat_system.repositories;

import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findByChatId(String s);
}
