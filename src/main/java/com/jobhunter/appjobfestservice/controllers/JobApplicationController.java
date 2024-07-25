package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import com.jobhunter.appjobfestservice.service.JobService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing job applications.
 * Provides endpoints for creating, updating, retrieving, and deleting job applications.
 * <p>
 * Security:
 * - Authentication and authorization are handled using JWT tokens.
 * - Roles: COMPANY.
 * <p>
 * Error Handling:
 * - Returns appropriate HTTP status codes for errors (e.g., 400 for bad requests, 404 for not found).
 * - Error responses include a message and a status code.
 */
@Tag(name = "Job Application Management", description = "APIs for managing job applications")
@RestController
@RequestMapping(JobApplicationController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class JobApplicationController {
    public static final String BASE_PATH = ConstantFields.BASE_PATH + "/job";

    private final JobService service;

    /**
     * Retrieve a job application by its ID.
     *
     * @param id the ID of the job application
     * @return the job application details
     */
    @Operation(summary = "Get job application by ID", description = "Retrieves a specific job application by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved job application",
                    content = @Content(schema = @Schema(implementation = JobApplicationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Job application not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Response<JobApplicationDTO> getJobByID(@Parameter(description = "ID of the job application to be retrieved", required = true)
                                                  @PathVariable String id) {
        log.info("Getting job application by ID: {}", id);
        return Response.successResponse(service.findById(id));
    }

    /**
     * Retrieve all job applications.
     *
     * @param size the number of job applications per page
     * @param page the page number to retrieve
     * @param id   the ID of the job application
     * @return a page of job applications
     */
    @Operation(summary = "Get all job applications", description = "Retrieves all job applications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved job applications",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all-jobs")
    public Response<Page<JobApplicationDTO>> getAllJobs(
            @Parameter(description = "Number of job applications per page", required = false)
            @RequestParam(value = "size", defaultValue = "10") int size,
            @Parameter(description = "Page number to retrieve", required = false)
            @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "ID of the job application", required = true)
            @PathVariable UUID id) {
        PageRequest pageRequest = PageRequest.of(size, page);
        log.info("Getting all job applications");
        return Response.successResponse(service.findAll(pageRequest, id));
    }

    /**
     * Create a new job application.
     *
     * @param dto the job application data to be created
     * @return the created job application
     */
    @Operation(summary = "Create a new job application", description = "Creates a new job application and returns the created application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created job application",
                    content = @Content(schema = @Schema(implementation = JobCreateDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @Authorize(permissions = {RoleEnum.COMPANY})
    public Response<JobCreateDTO> createJob(
            @Parameter(description = "Job application data to be created", required = true)
            @Valid @RequestBody JobCreateDTO dto) {
        log.info("Creating job: {}", dto);
        return Response.successResponse(service.create(dto));
    }

    /**
     * Update an existing job application.
     *
     * @param id  the ID of the job application to be updated
     * @param dto the updated job application data
     * @return the updated job application
     */
    @Operation(summary = "Update a job application", description = "Updates an existing job application and returns the updated application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated job application",
                    content = @Content(schema = @Schema(implementation = JobApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Job application not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<JobApplicationDTO> updateJob(
            @Parameter(description = "ID of the job application to be updated", required = true)
            @PathVariable String id,
            @Parameter(description = "Updated job application data", required = true)
            @RequestBody JobUpdateDTO dto) {
        log.info("Updating job application with ID {}: {}", id, dto);
        return Response.successResponse(service.update(id, dto));
    }

    /**
     * Delete a job application.
     *
     * @param id the ID of the job application to be deleted
     * @return a response indicating the deletion status
     */
    @Operation(summary = "Delete a job application", description = "Deletes a job application by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted job application"),
            @ApiResponse(responseCode = "404", description = "Job application not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<Void> deleteJob(
            @Parameter(description = "ID of the job application to be deleted", required = true)
            @PathVariable String id) {
        log.info("Deleting job application with ID: {}", id);
        service.deleteById(id);
        return Response.successResponse();
    }

    /**
     * Create multiple job applications.
     *
     * @param jobCreateDTOs the list of job applications to be created
     * @return the list of created job applications
     */
    @Operation(summary = "Create multiple job applications", description = "Creates multiple job applications and returns the created applications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created job applications",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/write-all")
    @Authorize(permissions = {RoleEnum.COMPANY})
    public Response<List<JobCreateDTO>> createMultiple(
            @Parameter(description = "List of job applications to be created", required = true)
            @Valid @RequestBody List<JobCreateDTO> jobCreateDTOs) {
        log.info("Creating multiple job applications: {}", jobCreateDTOs);
        return Response.successResponse(service.createMultiple(jobCreateDTOs));
    }

    /**
     * Search job applications by title.
     *
     * @param title the title to search by
     * @param size  the number of job applications per page
     * @param page  the page number to retrieve
     * @return a page of job applications that match the search criteria
     */
    @Operation(summary = "Search job applications by title", description = "Searches job applications by title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved job applications",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Job applications not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search-by-title")
    public Response<Page<JobApplicationDTO>> findByTitle(
            @Parameter(description = "Title to search by", required = true)
            @RequestParam String title,
            @Parameter(description = "Number of job applications per page", required = false)
            @RequestParam(value = "size", defaultValue = "10") int size,
            @Parameter(description = "Page number to retrieve", required = false)
            @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageRequest = PageRequest.of(size, page);
        log.info("Searching job applications by title: {}", title);
        return Response.successResponse(service.findByTitle(title, pageRequest));
    }
}
