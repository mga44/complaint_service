package com.mga44.complaint_service.complaint.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Slf4j
@Service
public class CountryService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${countryService.url}")
    private String countryServiceUrl;

    public Optional<String> resolveByIp(String ip) {
        var request = HttpRequest
                .newBuilder(URI.create(countryServiceUrl + ip))
                .GET()
                .build();

        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var ipResolveResponse = objectMapper.readValue(response.body(), IPResolveResponse.class);
            return Optional.of(ipResolveResponse.country);
        } catch (IOException | InterruptedException e) {
            log.error("There was an issue connecting to IP resolving service", e);
            return Optional.empty();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record IPResolveResponse(String country) {
    }
}
