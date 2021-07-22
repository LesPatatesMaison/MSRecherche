package org.patatesmaison.msrecherche.client;

import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConcentrateurApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${resource-base-url}")
    private String resourceBaseUrl;

    public <T> ResponseEntity<T> call(String endpoint, Class objectClass) {
        return this.restTemplate.getForEntity(resourceBaseUrl + endpoint, objectClass);
    }
}
