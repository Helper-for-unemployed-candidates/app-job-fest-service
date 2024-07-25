package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import com.jobhunter.appjobfestservice.entity.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    @Mapping(target = "description", source = "description")
    JobApplication createJobApplication(JobCreateDTO jobCreateDTO);

    @Mapping(target = "id", ignore = true)
    void updateJobApplication(@MappingTarget JobApplication jobApplication, JobUpdateDTO jobUpdateDTO);

    JobApplicationDTO toDto(JobApplication jobApplication);
    List<JobApplicationDTO> toListDTO(List<JobApplication> jobApplications);
    List<JobApplication> toCreateListDTO(List<JobCreateDTO> createDTOS);
    List<JobCreateDTO> listEntityToCreateDto(List<JobApplication> createDTOS);

default Page<JobApplicationDTO> toPageDTO(Page<JobApplication> jobApplications) {
    List<JobApplicationDTO> dtos = toListDTO(jobApplications.getContent());
    return new PageImpl<>(dtos, jobApplications.getPageable(), jobApplications.getTotalElements());
}

}
