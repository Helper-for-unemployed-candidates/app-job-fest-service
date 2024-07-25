package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExperienceDTO {
    private String id;
    @NotBlank(message = MessageConstants.COMPANY_NAME_CANNOT_BE_NULL)
    private String companyName;
    @NotNull(message = MessageConstants.START_DATE_CANNOT_BE_NULL)
    private Date fromDate;
    private Date toDate;
    @NotBlank(message = MessageConstants.POSITION_CANNOT_BE_NULL)
    private String position;
    @NotBlank(message = MessageConstants.DESCRIPTION_OF_EXPERIENCE_CANNOT_BE_NULL)
    private String description;
}
