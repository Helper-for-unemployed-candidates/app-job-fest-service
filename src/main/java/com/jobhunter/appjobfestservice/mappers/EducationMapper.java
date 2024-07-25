package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.EducationDTO;
import com.jobhunter.appjobfestservice.entity.Education;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EducationMapper {
    @Mapping(target = "specializationName", source = "specialization.name")
    EducationDTO toDTO(Education education);

    List<EducationDTO> toDTOs(List<Education> educations);

    Education toEntity(EducationDTO educationDTO);

    List<Education> toEntities(List<EducationDTO> educationDTOS);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Education education, EducationDTO educationDTO);
}
