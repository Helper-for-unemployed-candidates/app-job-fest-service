package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.Status;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobUpdateDTO {


    private String description;

    private Status status;

    private String title;
}
