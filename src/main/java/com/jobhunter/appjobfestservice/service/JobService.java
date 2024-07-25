package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface JobService {
    JobCreateDTO create(JobCreateDTO jobCreateDTO);
    List<JobCreateDTO> createMultiple(List<JobCreateDTO> jobCreateDTO);
    JobApplicationDTO update(String id, JobUpdateDTO jobUpdateDTO);
    JobApplicationDTO findById(String id);
    Page<JobApplicationDTO> findAll(Pageable pageable, UUID jobApplicationId);
    void deleteById(String id);
    Page<JobApplicationDTO> findByTitle(String name,Pageable pageable);
}
