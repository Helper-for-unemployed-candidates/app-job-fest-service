package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.*;
import com.jobhunter.appjobfestservice.entity.*;
import com.jobhunter.appjobfestservice.exceptions.RestException;
import com.jobhunter.appjobfestservice.mappers.*;
import com.jobhunter.appjobfestservice.repositories.*;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.payload.UserPrincipal;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import com.jobhunter.appjobfestservice.utils.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final SkillsRepository skillsRepository;
    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;
    private final ResumeMapper resumeMapper;
    private final ExperienceMapper experienceMapper;
    private final EducationMapper educationMapper;
    private final LanguageMapper languageMapper;

    @Override
    public Response<ResumeDTO> get(String id) {
        Resume resume = resumeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_RESUME_ID));
        return Response.successResponse(resumeMapper.toDTO(resume));
    }

    @Override
    @Transactional
    public Response<String> create(ResumeCreateDTO resumeDTO) {
        UserPrincipal user = ConstantFields.currentUser();
        if (resumeRepository.existsByCreatedByIdAndDeletedFalse(user.getId()))
            throw RestException.restThrow(MessageConstants.RESUME_ALREADY_EXISTS);
        Resume resume = Resume.builder()
                .about(resumeDTO.getAbout())
                .createdById(user.getId())
                .skills(resumeDTO.getSkills())
                .build();
        resume = resumeRepository.save(resume);

        Resume finalResume = resume;
        List<Experience> experiences = resumeDTO.getExperiences().stream()
                .map(experienceDTO -> {
                    Experience experience = experienceMapper.toEntity(experienceDTO);
                    experience.setCreatedById(user.getId());
                    experience.setResume(finalResume);
                    return experience;
                }).toList();
        experienceRepository.saveAll(experiences);

        List<Education> educations = resumeDTO.getEducations().stream()
                .map(educationDTO -> {
                    Education education = educationMapper.toEntity(educationDTO);
                    education.setCreatedById(user.getId());
                    if (educationDTO.getSpecializationId() != null) {
                        Specialization specialization = specializationRepository.findByIdAndDeletedFalse(educationDTO.getSpecializationId())
                                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_SPECIALIZATION_ID));
                        education.setSpecialization(specialization);
                    }
                    education.setResume(finalResume);
                    return education;
                }).toList();
        educationRepository.saveAll(educations);

        List<Language> languages = resumeDTO.getLanguages().stream()
                .map(languageDTO -> {
                    Language language = languageMapper.toEntity(languageDTO);
                    language.setCreatedById(user.getId());
                    language.setResume(finalResume);
                    return language;
                }).toList();
        languageRepository.saveAll(languages);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public Response<List<SkillProjection>> getSkills() {
        return Response.successResponse(skillsRepository.list());
    }

    @Override
    public Response<List<FacultyDTO>> getFaculties() {
        Map<Faculty, List<Specialization>> collect = specializationRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Specialization::getFaculty));
        return Response.successResponse(
                collect.keySet().stream()
                        .map(faculty ->
                                FacultyDTO.builder()
                                        .facultyName(faculty.getName())
                                        .specializations(specializationMapper.toDTOList(collect.get(faculty)))
                                        .build()
                        ).toList()
        );
    }

    @Override
    public Response<String> updateAbout(ResumeAboutDTO about) {
        UserPrincipal user = ConstantFields.currentUser();
        Resume resume = resumeRepository.findFirstByCreatedByIdAndDeletedFalse(user.getId())
                .orElseThrow(() -> RestException.restThrow(MessageConstants.PLEASE_CREATE_A_RESUME));
        resume.setAbout(about.getAbout());
        resumeRepository.save(resume);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public Response<String> updateSkills(ResumeSkillDTO skills) {
        UserPrincipal user = ConstantFields.currentUser();
        Resume resume = resumeRepository.findFirstByCreatedByIdAndDeletedFalse(user.getId())
                .orElseThrow(() -> RestException.restThrow(MessageConstants.PLEASE_CREATE_A_RESUME));
        resume.setSkills(skills.getSkills());
        resumeRepository.save(resume);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    @Transactional
    public Response<String> updateExperiences(ResumeExperienceDTO experiences) {
        List<Experience> list = experiences.getExperiences().stream()
                .map(experienceDTO -> {
                    if (experienceDTO.getId() != null) {
                        Experience experience = experienceRepository.findById(experienceDTO.getId())
                                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_EXPERIENCE_ID));
                        experienceMapper.update(experience, experienceDTO);
                        return experience;
                    } else return experienceMapper.toEntity(experienceDTO);
                }).toList();
        experienceRepository.saveAll(list);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public Response<String> updateEducations(ResumeEducationDTO educations) {
        List<Education> list = educations.getEducations().stream()
                .map(educationDTO -> {
                    Education education;
                    if (educationDTO.getId() != null) {
                        education = educationRepository.findById(educationDTO.getId())
                                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_EDUCATION_ID));
                        educationMapper.update(education, educationDTO);
                    } else education = educationMapper.toEntity(educationDTO);

                    if (educationDTO.getSpecializationId() != null) {
                        Specialization specialization = specializationRepository.findByIdAndDeletedFalse(educationDTO.getSpecializationId())
                                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_SPECIALIZATION_ID));
                        education.setSpecialization(specialization);
                    }
                    return education;
                }).toList();
        educationRepository.saveAll(list);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public Response<String> updateLanguages(ResumeLanguageDTO languages) {
        List<Language> list = languages.getLanguages().stream()
                .map(languageDTO -> {
                    if (languageDTO.getId() != null) {
                        Language language = languageRepository.findById(languageDTO.getId())
                                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_LANGUAGE_ID));
                        languageMapper.update(language, languageDTO);
                        return language;
                    } else return languageMapper.toEntity(languageDTO);
                }).toList();
        languageRepository.saveAll(list);

        return Response.successResponse(MessageConstants.SUCCESS);
    }

    @Override
    public Response<String> delete(String id) {
        UserPrincipal user = ConstantFields.currentUser();
        Resume resume = resumeRepository.findByIdAndCreatedById(id, user.getId())
                .orElseThrow(() -> RestException.restThrow(MessageConstants.INVALID_RESUME_ID));
        resume.setDeleted(true);
        resumeRepository.save(resume);
        return Response.successResponse(MessageConstants.SUCCESS);
    }

    /*TODO they must be completed tomorrow   25 JULY
     * 1. complete resume section inside of job-service -> done
     * 2. complete the controller class of submission -> done
     * 3. chat system integration
     * 4. AI integration
     * 5. DOCUMENTATION -> done
     * 6.
     * */
}
