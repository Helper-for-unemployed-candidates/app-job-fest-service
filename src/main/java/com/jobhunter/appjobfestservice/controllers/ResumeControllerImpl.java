package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
import com.jobhunter.appjobfestservice.service.ResumeService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeControllerImpl implements ResumeController {
    private final ResumeService resumeService;

    @Override
    public Response<ResumeDTO> get(String id) {
        return resumeService.get(id);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> create(ResumeCreateDTO resumeDTO) {
        return resumeService.create(resumeDTO);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<List<SkillProjection>> getSkills() {
        return resumeService.getSkills();
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<List<FacultyDTO>> getFaculties() {
        return resumeService.getFaculties();
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> updateAbout(ResumeAboutDTO about) {
        return resumeService.updateAbout(about);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> updateSkills(ResumeSkillDTO skills) {
        return resumeService.updateSkills(skills);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> updateExperiences(ResumeExperienceDTO experiences) {
        return resumeService.updateExperiences(experiences);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> updateEducations(ResumeEducationDTO educations) {
        return resumeService.updateEducations(educations);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> updateLanguages(ResumeLanguageDTO languages) {
        return resumeService.updateLanguages(languages);
    }

    @Authorize(permissions = RoleEnum.APPLICANT)
    @Override
    public Response<String> delete(String id) {
        return resumeService.delete(id);
    }
}
