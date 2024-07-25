package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
import com.jobhunter.appjobfestservice.shit.payload.Response;

import java.util.List;

public interface ResumeService {
    Response<ResumeDTO> get(String id);

    Response<String> create(ResumeCreateDTO resumeDTO);

    Response<List<SkillProjection>> getSkills();

    Response<List<FacultyDTO>> getFaculties();

    Response<String> updateAbout(ResumeAboutDTO about);

    Response<String> updateSkills(ResumeSkillDTO skills);

    Response<String> updateExperiences(ResumeExperienceDTO experiences);

    Response<String> updateEducations(ResumeEducationDTO educations);

    Response<String> updateLanguages(ResumeLanguageDTO languages);

    Response<String> delete(String id);
}
