package com.example.goeuro;

import com.example.goeuro.service.CsvSuggestionConverter;
import com.example.goeuro.service.CsvSuggestionWritter;
import com.example.goeuro.service.GoEuroApiClient;
import com.google.common.collect.ImmutableList;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class GoEuroApplication implements CommandLineRunner {
    @Autowired
    private CsvSuggestionWritter csvSuggestionWritter;
    @Autowired
    private GoEuroApiClient goEuroApiClient;
    @Autowired
    private CsvSuggestionConverter csvSuggestionConverter;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }
    public static void main(String[] args) {
        new SpringApplicationBuilder(GoEuroApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
    @Override
    public void run(String... args) throws Exception {
        String cityName = args[0].trim();
        String fileName = cityName + ".csv";
        csvSuggestionWritter.write(fileName, goEuroApiClient.findSuggestionsByCity(cityName).stream()
                .map(csvSuggestionConverter::tocsvSuggestionDto)
                .collect(collectingAndThen(toList(),
                        ImmutableList::copyOf)));
    }
}
