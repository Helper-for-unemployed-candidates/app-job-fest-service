package com.jobhunter.appjobfestservice.service;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import com.jobhunter.appjobfestservice.entity.JobApplication;
import com.jobhunter.appjobfestservice.exceptions.RestException;
import com.jobhunter.appjobfestservice.mappers.JobApplicationMapper;
import com.jobhunter.appjobfestservice.repositories.JobApplicationRepository;
import com.jobhunter.appjobfestservice.shit.payload.UserPrincipal;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobApplicationRepository repository;
    private final JobApplicationMapper mapper;

    @Override
    public JobCreateDTO create(JobCreateDTO jobCreateDTO) {
        log.info("Creating job ...");
        JobApplication jobApplication = mapper.createJobApplication(jobCreateDTO);
        log.info("Successfully created a job !");
        repository.save(jobApplication);
        return jobCreateDTO;
    }

    @Override
    public List<JobCreateDTO> createMultiple(List<JobCreateDTO> jobCreateDTO) {
        log.info("Creating multiple job ...");

        List<JobApplication> createList = mapper.toCreateListDTO(jobCreateDTO);
        log.info("Successfully created multiple job !");
        repository.saveAll(createList);
        return mapper.listEntityToCreateDto(createList);
    }

    @Override
    @Transactional
    @CachePut(value = "jobApplications", key = "keyGenerator")
    public JobApplicationDTO update(UUID id, JobUpdateDTO jobUpdateDTO) {
        UserPrincipal user = ConstantFields.currentUser(); // authorized

        log.info("Updating job ...");
        JobApplication existingJob = repository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> RestException.restThrow("Job application not found with id: " + id));

        mapper.updateJobApplication(existingJob, jobUpdateDTO);
        JobApplicationDTO dto = mapper.toDto(existingJob);
        repository.save(existingJob);
        return dto;
    }

    @Override
    @Cacheable(value = "jobApplication", key = "#id")
    public JobApplicationDTO findById(UUID id) {
        log.info("Retrieving job application ...");
        JobApplication jobApplication = repository
                .findById(id)
                .orElseThrow(() -> RestException.restThrow("Job application not found with userId:  " + id));
        return mapper.toDto(jobApplication);

    }

    @Override
    public Page<JobApplicationDTO> findAll(Pageable pageable, UUID jobApplicationId) {
        log.info("Finding all job applications ...");
        Page<JobApplication> all = repository.findJobApplicationById(pageable, jobApplicationId);

        return mapper.toPageDTO(all);
    }

    @Override
    @CacheEvict(value = "jobApplications", key = "#id")
    public void deleteById(UUID id) {
        UserPrincipal user = ConstantFields.currentUser();
        repository.deleteByIdAndUserId(id, user.getId());
    }

    @Override
    @Cacheable(value = "jobApplicationDTO", key = "#keyGenerator")
    public Page<JobApplicationDTO> findByTitle(String title, Pageable pageable) {
        log.info("Finding job applications by title ...");
        Page<JobApplication> application = repository.findJobApplicationByTitleContaining(title, pageable);
        return mapper.toPageDTO(application);
    }

}
/// config for cachingEvict
/// pagination for search
/// userId lani constant.getCurrentUser() @Authorized bogan bosa ishlidi


/// findByTitle should be implemented in controller