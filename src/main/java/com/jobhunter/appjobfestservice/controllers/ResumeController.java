package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ResumeController.BASE_PATH)
public interface ResumeController {
    String BASE_PATH = ConstantFields.BASE_PATH + "/resume";
    String UPDATE_ABOUT_PATH = "/about";
    String SKILLS_PATH = "/skills";
    String EXPERIENCES_PATH = "/experience";
    String EDUCATIONS_PATH = "/education";
    String LANGUAGES_PATH = "/language";

    @GetMapping("/{id}")
    Response<ResumeDTO> get(@PathVariable String id);

    @PostMapping
    Response<String> create(@RequestBody @Valid ResumeCreateDTO resumeDTO);

    @GetMapping(SKILLS_PATH)
    Response<List<SkillProjection>> getSkills();

    @GetMapping(EDUCATIONS_PATH)
    Response<List<FacultyDTO>> getFaculties();

    @PutMapping(UPDATE_ABOUT_PATH)
    Response<String> updateAbout(@RequestBody @Valid ResumeAboutDTO about);

    @PutMapping(SKILLS_PATH)
    Response<String> updateSkills(@RequestBody @Valid ResumeSkillDTO skills);

    @PutMapping(EXPERIENCES_PATH)
    Response<String> updateExperiences(@RequestBody @Valid ResumeExperienceDTO skills);

    @PutMapping(EDUCATIONS_PATH)
    Response<String> updateEducations(@RequestBody @Valid ResumeEducationDTO skills);

    @PutMapping(LANGUAGES_PATH)
    Response<String> updateLanguages(@RequestBody @Valid ResumeLanguageDTO skills);

    @DeleteMapping
    Response<String> delete(String id);
}
