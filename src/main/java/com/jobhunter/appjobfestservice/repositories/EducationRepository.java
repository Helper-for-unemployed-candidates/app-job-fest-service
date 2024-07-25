package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Education;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends MongoRepository<Education, String> {

}
