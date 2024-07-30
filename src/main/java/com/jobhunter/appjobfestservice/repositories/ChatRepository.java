package com.jobhunter.appjobfestservice.repositories;

import com.jobhunter.appjobfestservice.entity.Chat;
import com.jobhunter.appjobfestservice.repositories.projection.ChatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    Page<ChatProjection> findByApplicantId(UUID userId, Pageable pageable);

    Page<ChatProjection> findByCompanyIdAndJobApplication_Id(UUID companyId, String jopApplication, Pageable pageable);
}
