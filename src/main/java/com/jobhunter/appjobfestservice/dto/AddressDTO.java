package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.Address;
import com.jobhunter.appjobfestservice.entity.City;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO  {

    private CityDTO city;
    private String fullAddress;
}
