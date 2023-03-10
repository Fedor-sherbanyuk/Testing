package com.example.goeuro.service;

import com.example.goeuro.domain.Suggestion;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GoEuroApiClient {
    private final RestTemplate restTemplate;

    public GoEuroApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${application.suggestionUrlTemplate}")
    private String suggestionUrl;

    public List<Suggestion> findSuggestionsByCity(@NonNull String city) {
        ResponseEntity<Suggestion[]> response = restTemplate.getForEntity(suggestionUrl, Suggestion[].class,
                ImmutableMap.of("city", city));
        return ImmutableList.copyOf(response.getBody());
    }
}
