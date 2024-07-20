package com.jobhunter.appjobfestservice.shit.payload;

import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPrincipal {
    UUID id;
    String phone;
    String email;
    UUID avatarId;
    AddressDTO address;
    RoleEnum role;
    ApplicantDTO applicant;
    CompanyDTO company;
}
