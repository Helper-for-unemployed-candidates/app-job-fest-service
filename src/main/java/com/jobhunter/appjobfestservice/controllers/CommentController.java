package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.CommentDTO;
import com.jobhunter.appjobfestservice.service.CommentService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for managing comments.
 * Provides endpoints for creating, retrieving, and deleting comments.
 * <p>
 * Security:
 * - Authentication and authorization are handled using JWT tokens.
 * - Roles: APPLICANT, COMPANY, ADMIN.
 * - Permissions are checked using the @Authorize annotation.
 * <p>
 * Error Handling:
 * - Returns appropriate HTTP status codes for errors (e.g., 400 for bad requests, 404 for not found).
 * - Error responses include a message and a status code.
 */
@Tag(name = "Comment management", description = "Comment controller with CRUD operation")
@RestController

@RequestMapping(CommentController.BASE_PATH)
@RequiredArgsConstructor
public class CommentController {
    public static final String BASE_PATH = ConstantFields.BASE_PATH + "/comments";
    private final CommentService commentService;

    /**
     * Retrieve all comments for a given application.
     *
     * @param size          the number of comments per page (default is 10)
     * @param page          the page number to retrieve (default is 1)
     * @param applicationId the ID of the application
     * @return a paginated list of comments
     */
    @Operation(summary = "Retrieve all comments for a given application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of comments"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{applicationId}")
    public Response<Page<CommentDTO>> findAll(
            @Parameter(description = "Number of comments per page") @RequestParam(value = "size", defaultValue = "10") int size,
            @Parameter(description = "Page number to retrieve") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "ID of the application") @PathVariable String applicationId) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return Response.successResponse(commentService.findAll(pageRequest, applicationId));
    }

    /**
     * Retrieve a specific comment by its ID.
     *
     * @param id the ID of the comment
     * @return the comment details
     */
    @Operation(summary = "Retrieve a specific comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the comment"),
            @ApiResponse(responseCode = "404", description = "Comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get/{id}")
    @Cacheable(value = "commentDto", key = "#id")
    public Response<CommentDTO> getComment(
            @Parameter(description = "ID of the comment") @PathVariable String id) {
        return Response.successResponse(commentService.getComment(id));
    }

    /**
     * Create a new comment.
     * Requires APPLICANT or COMPANY role.
     *
     * @param comment the comment details
     * @return the created comment
     */
    @Operation(summary = "Create a new comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the comment"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/write")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.COMPANY})
    public Response<CommentDTO> createComment(
            @Parameter(description = "Comment details") @Valid @RequestBody CommentDTO comment) {
        return Response.successResponse(commentService.writeComment(comment));
    }

    /**
     * Delete a comment by its ID.
     * Requires APPLICANT or ADMIN role.
     *
     * @param id the ID of the comment
     * @return a response entity with no content
     */
    @Operation(summary = "Delete a comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the comment"),
            @ApiResponse(responseCode = "404", description = "Comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "deleteComment", key = "#id")
    @Authorize(permissions = {RoleEnum.APPLICANT, RoleEnum.ADMIN})
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID of the comment") @PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

