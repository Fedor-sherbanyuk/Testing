package com.example.goeuro.service;

import com.example.goeuro.domain.Suggestion;
import com.example.goeuro.dto.CsvSuggestionDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CsvSuggestionConverter {
    public CsvSuggestionDto tocsvSuggestionDto(@NonNull Suggestion suggestion) {
        CsvSuggestionDto dto = new CsvSuggestionDto();
        dto.setId(suggestion.getId());
        dto.setName(suggestion.getName());
        dto.setType(suggestion.getType());
        dto.setLatitude(suggestion.getGeoPosition().getLatitude());
        dto.setLongitude(suggestion.getGeoPosition().getLongitude());
        return dto;
    }
}
