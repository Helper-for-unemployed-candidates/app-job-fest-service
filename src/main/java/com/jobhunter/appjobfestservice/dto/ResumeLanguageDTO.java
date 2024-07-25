package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeLanguageDTO {
    @Valid
    @NotNull(message = MessageConstants.LANGUAGES_LIST_CANNOT_BE_NULL)
    List<LanguageDTO> languages;
}
