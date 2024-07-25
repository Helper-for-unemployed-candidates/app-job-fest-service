package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.TypeOfEducation;
import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EducationDTO {
    private String id;
    @NotNull(message = MessageConstants.TYPE_OF_EDUCATION_CANNOT_BE_NULL)
    private TypeOfEducation type;
    @NotBlank(message = MessageConstants.NAME_OF_EDUCATION_CANNOT_BE_NULL)
    private String name;
    private String specializationId;
    private String specializationName;
    @NotNull(message = MessageConstants.START_DATE_CANNOT_BE_NULL)
    private Date fromDate;
    private Date toDate;
}
