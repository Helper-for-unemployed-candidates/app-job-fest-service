package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.service.CommentService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{applicationId}")
//    @Authorize(permissions = RoleEnum.APPLICANT)
    public Response<Page<CommentDTO>> findAll(@RequestParam(value = "size", defaultValue = "10") int size,
                                              @RequestParam(value = "page", defaultValue = "1") int page, @PathVariable UUID applicationId) {
        PageRequest pageRequest = PageRequest.of(size, page);
        return Response.successResponse(commentService.findAll(pageRequest,applicationId));
    }

    @GetMapping("/get/{id}")
    @Cacheable(value = "commentDto", key = "#id")
    public Response<CommentDTO> getComment(@PathVariable UUID id) {
        return Response.successResponse(commentService.getComment(id));
    }

    //    @Authorize(permissions = RoleEnum.APPLICANT)
    @PostMapping("/write")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<CommentDTO> createComment(@RequestBody CommentDTO comment) {

        return Response.successResponse(commentService.writeComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "deleteComment", key = "#id")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.ADMIN})
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }


}
