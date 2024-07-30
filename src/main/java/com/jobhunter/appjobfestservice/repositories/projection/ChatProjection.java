package com.jobhunter.appjobfestservice.repositories.projection;

import com.jobhunter.appjobfestservice.entity.JobApplication;
import org.springframework.beans.factory.annotation.Value;

public interface ChatProjection {
    String getId();

    String getJobApplication();

    String getResume();

    String getStatus();
}
