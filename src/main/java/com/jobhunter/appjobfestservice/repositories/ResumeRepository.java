package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {
    Optional<Resume> findByIdAndDeletedFalse(String id);

    boolean existsByCreatedByIdAndDeletedFalse(UUID id);

    Optional<Resume> findFirstByCreatedByIdAndDeletedFalse(UUID id);

    Optional<Resume> findByIdAndCreatedById(String id, UUID createdById);
}
