package com.jobhunter.appjobfestservice.dto;


import com.jobhunter.appjobfestservice.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCreateDTO  {
    @NotNull(message = "Job type cannot bu null")
    private JobTypeDTO jobType;
    private String description;
    @NotNull(message = "requirements should be filled")
    private RequirementDTO requirements;
    private Status status;
    @NotNull(message = "address should be filled")
    private AddressDTO address;
    private String title;
}
