package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.JobApplication;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
    // takes job with the first pattern letters
    Page<JobApplication> findJobApplicationByTitleContaining(String titlePattern, Pageable pageable);
    Page<JobApplication> findJobApplicationById(Pageable pageable, UUID id);

    boolean deleteByIdAndUserId(UUID id, UUID userId);
    Optional<JobApplication> findByIdAndUserId(UUID id, UUID userId);
}
