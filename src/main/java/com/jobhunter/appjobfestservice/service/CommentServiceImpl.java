package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.entity.Comment;
import com.jobhunter.appjobfestservice.exceptions.RestException;
import com.jobhunter.appjobfestservice.mappers.CommentMapper;
import com.jobhunter.appjobfestservice.repositories.CommentRepository;
import com.jobhunter.appjobfestservice.shit.payload.UserPrincipal;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDTO writeComment(CommentDTO dto) {
        UserPrincipal userPrincipal = ConstantFields.currentUser();

        Comment comment = commentMapper.commentDTOToComment(dto);
        comment.setUserId(userPrincipal.getId());
        commentRepository.save(comment);
        log.info("Comment saved");
        return commentMapper.commentToCommentDTO(comment);

    }


    @Override
    public Page<CommentDTO> findAll(Pageable pageable, UUID jobApplicationId) {
        Page<Comment> all = commentRepository.findAllByJobApplicationId(jobApplicationId, pageable);
        return commentMapper.toPageDTO(all);
    }

    @Override
    @Cacheable(value = "comment", key = "#id")
    public CommentDTO getComment(UUID id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> RestException.restThrow("Comment not found"));
        return commentMapper.commentToCommentDTO(comment);
    }

    @Override
    @CacheEvict(value = "comment", key = "#id")
    public void deleteComment(UUID id) {
        UserPrincipal user = ConstantFields.currentUser();
        commentRepository.deleteByIdAndUserId(id, user.getId());
    }
}

///