package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Language;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {
}
