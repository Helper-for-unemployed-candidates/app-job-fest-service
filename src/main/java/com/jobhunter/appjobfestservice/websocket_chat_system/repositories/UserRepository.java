package com.jobhunter.appjobfestservice.websocket_chat_system.repositories;

import com.jobhunter.appjobfestservice.websocket_chat_system.enums.Status;
import com.jobhunter.appjobfestservice.websocket_chat_system.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
}
