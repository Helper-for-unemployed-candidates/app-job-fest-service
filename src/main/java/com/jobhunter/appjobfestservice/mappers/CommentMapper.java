package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.entity.Comment;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO commentToCommentDTO(Comment comment);
    Comment commentDTOToComment(CommentDTO commentDTO);
    List<CommentDTO> commentsToCommentDTOs(List<Comment> comments);

    List<CommentDTO> toListDTO(List<Comment> comments);

    default Page<CommentDTO> toPageDTO(Page<Comment> comments) {
        List<CommentDTO> listDTO = toListDTO(comments.getContent());
        return new PageImpl<>(listDTO, comments.getPageable(), comments.getTotalElements());
    }

}
