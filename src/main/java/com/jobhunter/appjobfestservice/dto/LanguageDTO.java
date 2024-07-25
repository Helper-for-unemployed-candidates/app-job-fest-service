package com.jobhunter.appjobfestservice.dto;

import com.jobhunter.appjobfestservice.entity.enums.Languages;
import com.jobhunter.appjobfestservice.entity.enums.Level;
import com.jobhunter.appjobfestservice.utils.MessageConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageDTO {
    private String id;
    @NotNull(message = MessageConstants.LANGUAGE_CANNOT_BE_NULL)
    private Languages languages;
    @NotNull(message = MessageConstants.LANGUAGE_LEVEL_CANNOT_BE_NULL)
    private Level level;
}
