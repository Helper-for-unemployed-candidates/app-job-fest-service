package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.SpecializationDTO;
import com.jobhunter.appjobfestservice.entity.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE // compile time da warninglani ignor qvoradi
)
public interface SpecializationMapper {
    SpecializationDTO toDTO(Specialization specialization);
    List<SpecializationDTO> toDTOList(List<Specialization> specializations);
}
