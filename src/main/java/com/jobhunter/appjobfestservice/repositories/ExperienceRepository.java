package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends MongoRepository<Experience, String> {
}
