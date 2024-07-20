package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.Region;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    private String cityName;
    private Region region;
}
