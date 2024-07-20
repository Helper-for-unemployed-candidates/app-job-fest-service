package com.jobhunter.appjobfestservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobTypeDTO {
    private String name;
    private CategoryDTO category;
}
