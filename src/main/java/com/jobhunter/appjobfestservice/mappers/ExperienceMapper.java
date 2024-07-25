package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.ExperienceDTO;
import com.jobhunter.appjobfestservice.entity.Experience;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ExperienceMapper {
    ExperienceDTO toDTO(Experience experience);

    List<ExperienceDTO> toDTO(List<Experience> experiences);

    Experience toEntity(ExperienceDTO experienceDTO);

    List<Experience> toEntity(List<ExperienceDTO> experienceDTOs);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Experience experience, ExperienceDTO experienceDTO);
}
