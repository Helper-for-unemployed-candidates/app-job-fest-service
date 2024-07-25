package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.ResumeDTO;
import com.jobhunter.appjobfestservice.entity.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                EducationMapper.class,
                ExperienceMapper.class,
                LanguageMapper.class
        }
)
public interface ResumeMapper {
    ResumeDTO toDTO(Resume resume);

    Resume toEntity(ResumeDTO resumeDTO);
}
