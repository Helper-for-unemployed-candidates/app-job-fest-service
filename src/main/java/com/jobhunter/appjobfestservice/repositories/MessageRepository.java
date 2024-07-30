package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    Slice<Message> findByChatIdOrderByCreatedAtDesc(String chatId, Pageable pageable);
}
