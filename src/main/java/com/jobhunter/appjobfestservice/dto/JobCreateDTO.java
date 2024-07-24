package com.jobhunter.appjobfestservice.dto;


import com.jobhunter.appjobfestservice.entity.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCreateDTO  {
    private JobTypeDTO jobType;
    private String description;

    private RequirementDTO requirements;
    private Status status;

    private AddressDTO address;
    private String title;
}
