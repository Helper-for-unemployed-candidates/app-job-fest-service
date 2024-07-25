package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends MongoRepository<Faculty, String> {
}
