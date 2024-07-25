package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {
    CommentDTO writeComment(CommentDTO dto);

    Page<CommentDTO> findAll(Pageable pageable, String applicationId);

    CommentDTO getComment(String id);

    void deleteComment(String id);
}
