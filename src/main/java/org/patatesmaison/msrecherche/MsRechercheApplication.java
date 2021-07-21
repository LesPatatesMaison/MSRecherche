package org.patatesmaison.msrecherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsRechercheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsRechercheApplication.class, args);
    }

}
