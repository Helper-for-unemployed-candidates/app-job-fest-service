package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Specialization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends MongoRepository<Specialization, String> {
    Optional<Specialization> findByIdAndDeletedFalse(String specializationId);
}
