package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.service.CommentService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommentController.BASE_PATH)
@RequiredArgsConstructor
public class CommentController {
    public static final String BASE_PATH = ConstantFields.BASE_PATH + "/comments";
    private final CommentService commentService;

    @GetMapping("/{applicationId}")
    public Response<Page<CommentDTO>> findAll(@RequestParam(value = "size", defaultValue = "10") int size,
                                              @RequestParam(value = "page", defaultValue = "1") int page, @PathVariable String applicationId) {
        PageRequest pageRequest = PageRequest.of(size, page);
        return Response.successResponse(commentService.findAll(pageRequest, applicationId));
    }

    @GetMapping("/get/{id}")
    @Cacheable(value = "commentDto", key = "#id")
    public Response<CommentDTO> getComment(@PathVariable String id) {
        return Response.successResponse(commentService.getComment(id));
    }

    @PostMapping("/write")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<CommentDTO> createComment(@RequestBody CommentDTO comment) {

        return Response.successResponse(commentService.writeComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "deleteComment", key = "#id")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.ADMIN})
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
