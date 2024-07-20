package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    boolean deleteByIdAndUserId(UUID id, UUID userId);
    Page<Comment> findAllByJobApplicationId( UUID jobApplicationId, Pageable pageable);
}
