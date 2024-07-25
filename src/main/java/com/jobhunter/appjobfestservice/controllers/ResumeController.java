package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API interface for managing resumes.
 * Provides endpoints for creating, retrieving, updating, and deleting resume details.
 * <p>
 * Error Handling:
 * - Returns appropriate HTTP status codes for errors (e.g., 400 for bad requests, 404 for not found).
 * - Error responses include a message and a status code.
 */
@Tag(name = "Resume Management", description = "APIs for managing resumes")
@RequestMapping(ResumeController.BASE_PATH)
public interface ResumeController {
    String BASE_PATH = ConstantFields.BASE_PATH + "/resume";
    String UPDATE_ABOUT_PATH = "/about";
    String SKILLS_PATH = "/skills";
    String EXPERIENCES_PATH = "/experience";
    String EDUCATIONS_PATH = "/education";
    String LANGUAGES_PATH = "/language";

    /**
     * Retrieve a resume by its ID.
     *
     * @param id the ID of the resume
     * @return the resume details
     */
    @Operation(summary = "Get resume by ID", description = "Retrieves a specific resume by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resume",
                    content = @Content(schema = @Schema(implementation = ResumeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resume not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    Response<ResumeDTO> get(
            @Parameter(description = "ID of the resume to be retrieved", required = true)
            @PathVariable String id);

    /**
     * Create a new resume.
     *
     * @param resumeDTO the resume data to be created
     * @return the ID of the created resume
     */
    @Operation(summary = "Create a new resume", description = "Creates a new resume and returns the ID of the created resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created resume",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    Response<String> create(
            @Parameter(description = "Resume data to be created", required = true)
            @RequestBody @Valid ResumeCreateDTO resumeDTO);

    /**
     * Retrieve all skills associated with resumes.
     *
     * @return a list of skills
     */
    @Operation(summary = "Get all skills", description = "Retrieves all skills associated with resumes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved skills",
                    content = @Content(schema = @Schema(implementation = SkillProjection.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(SKILLS_PATH)
    Response<List<SkillProjection>> getSkills();

    /**
     * Retrieve all faculties associated with resumes.
     *
     * @return a list of faculties
     */
    @Operation(summary = "Get all faculties", description = "Retrieves all faculties associated with resumes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved faculties",
                    content = @Content(schema = @Schema(implementation = FacultyDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(EDUCATIONS_PATH)
    Response<List<FacultyDTO>> getFaculties();

    /**
     * Update the 'about' section of a resume.
     *
     * @param about the updated 'about' section
     * @return a confirmation message
     */
    @Operation(summary = "Update 'about' section of resume", description = "Updates the 'about' section of a resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated 'about' section",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(UPDATE_ABOUT_PATH)
    Response<String> updateAbout(
            @Parameter(description = "Updated 'about' section data", required = true)
            @RequestBody @Valid ResumeAboutDTO about);

    /**
     * Update the skills section of a resume.
     *
     * @param skills the updated skills section
     * @return a confirmation message
     */
    @Operation(summary = "Update skills section of resume", description = "Updates the skills section of a resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated skills section",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(SKILLS_PATH)
    Response<String> updateSkills(
            @Parameter(description = "Updated skills section data", required = true)
            @RequestBody @Valid ResumeSkillDTO skills);

    /**
     * Update the experiences section of a resume.
     *
     * @param experiences the updated experiences section
     * @return a confirmation message
     */
    @Operation(summary = "Update experiences section of resume", description = "Updates the experiences section of a resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated experiences section",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(EXPERIENCES_PATH)
    Response<String> updateExperiences(
            @Parameter(description = "Updated experiences section data", required = true)
            @RequestBody @Valid ResumeExperienceDTO experiences);

    /**
     * Update the education section of a resume.
     *
     * @param educations the updated education section
     * @return a confirmation message
     */
    @Operation(summary = "Update education section of resume", description = "Updates the education section of a resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated education section",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(EDUCATIONS_PATH)
    Response<String> updateEducations(
            @Parameter(description = "Updated education section data", required = true)
            @RequestBody @Valid ResumeEducationDTO educations);

    /**
     * Update the languages section of a resume.
     *
     * @param languages the updated languages section
     * @return a confirmation message
     */
    @Operation(summary = "Update languages section of resume", description = "Updates the languages section of a resume.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated languages section",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(LANGUAGES_PATH)
    Response<String> updateLanguages(
            @Parameter(description = "Updated languages section data", required = true)
            @RequestBody @Valid ResumeLanguageDTO languages);

    /**
     * Delete a resume by its ID.
     *
     * @param id the ID of the resume to be deleted
     * @return a confirmation message
     */
    @Operation(summary = "Delete resume by ID", description = "Deletes a resume by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted resume",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Resume not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping
    Response<String> delete(
            @Parameter(description = "ID of the resume to be deleted", required = true)
            @RequestParam String id);
}
