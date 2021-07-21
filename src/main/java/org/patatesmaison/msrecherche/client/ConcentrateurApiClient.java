package org.patatesmaison.msrecherche.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConcentrateurApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

}
