package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface JobService {
    JobCreateDTO create(JobCreateDTO jobCreateDTO);
    List<JobCreateDTO> createMultiple(List<JobCreateDTO> jobCreateDTO);
    JobApplicationDTO update(UUID id, JobUpdateDTO jobUpdateDTO);
    JobApplicationDTO findById(UUID id);
    Page<JobApplicationDTO> findAll(Pageable pageable);
    void deleteById(UUID id);
    Response<Page<JobApplicationDTO>> findByTitle(String name,Pageable pageable);
}
