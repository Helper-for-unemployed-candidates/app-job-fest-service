package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    // takes job with the first pattern letters
    Page<JobApplication> findJobApplicationByTitleContaining(String titlePattern, Pageable pageable);

    Page<JobApplication> findJobApplicationById(Pageable pageable, UUID id);

    void deleteByIdAndCreatedById(String id, UUID userId);

    Optional<JobApplication> findByIdAndCreatedById(String id, UUID userId);
}
