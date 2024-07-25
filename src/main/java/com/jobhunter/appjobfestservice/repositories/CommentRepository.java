package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CommentRepository extends MongoRepository<Comment, String> {
    void deleteByIdAndUserId(String id, UUID userId);

    Page<Comment> findAllByJobApplicationId(String jobApplicationId, Pageable pageable);
}
