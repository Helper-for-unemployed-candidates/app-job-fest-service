package com.jobhunter.appjobfestservice.websocket_chat_system.repositories;

import com.jobhunter.appjobfestservice.websocket_chat_system.entities.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRepository extends MongoRepository<ChatRoom,String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String receiverId);
}
