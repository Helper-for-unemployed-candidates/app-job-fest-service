package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Skills;
import com.jobhunter.appjobfestservice.repositories.projection.SkillProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends MongoRepository<Skills, String> {
    @Query(value = "{}", fields = "{ 'name' : 1 }")
    List<SkillProjection> list();
}
