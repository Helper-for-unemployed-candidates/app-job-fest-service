package com.jobhunter.appjobfestservice.mappers;

import com.jobhunter.appjobfestservice.dto.LanguageDTO;
import com.jobhunter.appjobfestservice.entity.Language;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LanguageMapper {
    LanguageDTO toDTO(Language language);

    List<LanguageDTO> toDTOs(List<Language> languages);

    Language toEntity(LanguageDTO languageDTO);

    List<Language> toEntities(List<LanguageDTO> languageDTOs);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Language language, LanguageDTO languageDTO);
}
