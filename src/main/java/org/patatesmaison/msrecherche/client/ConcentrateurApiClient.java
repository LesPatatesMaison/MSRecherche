package org.patatesmaison.msrecherche.client;

import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConcentrateurApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${resource-base-url}")
    private String resourceBaseUrl;

    @Value("${concentrateur.url.bar}")
    private String routeBar;

    @Value("${concentrateur.url.cocktail}")
    private String routeCocktail;

    // pour mémoire parcequ'elle était cool
    public <T> T call(String endpoint, Class<T> objectClass) {
        return this.restTemplate.getForObject(resourceBaseUrl + endpoint, objectClass);
    }

    public BarDTO getBarById(Long barId) {
        return this.restTemplate.getForObject(String.format("%s/%d", routeBar, barId), BarDTO.class);
    }

    public List<BarDTO> findBarByName(String barName) {
        BarDTO[] barDTOArray = this.restTemplate.getForObject(String.format("%s/search?name=%s", routeBar, barName), BarDTO[].class);
        return barDTOArray == null ? new ArrayList<>() : Arrays.asList(barDTOArray);
    }

    public List<BarDTO> getBarList() {
        BarDTO[] barDTOArray = this.restTemplate.getForObject(routeBar, BarDTO[].class);
        return barDTOArray == null ? new ArrayList<>() : Arrays.asList(barDTOArray);
    }

    public List<CocktailDTO> findCocktailsByName(String cocktailName) {
        CocktailDTO[] cocktailDTOArray = this.restTemplate.getForObject(String.format("%s/%s", routeCocktail, cocktailName), CocktailDTO[].class);
        return cocktailDTOArray == null ? new ArrayList<>() : Arrays.asList(cocktailDTOArray);
    }

    public List<CocktailDTO> findCocktailsByIngredient(String ingredient) {
        CocktailDTO[] cocktailDTOArray = this.restTemplate.getForObject(String.format("%s/ingredient/%s", routeCocktail, ingredient), CocktailDTO[].class);
        return cocktailDTOArray == null ? new ArrayList<>() : Arrays.asList(cocktailDTOArray);
    }

    public CocktailDTO getCocktailById(Long cocktailID) {
        return this.restTemplate.getForObject(String.format("%s/id/%d", routeCocktail, cocktailID), CocktailDTO.class);
    }
}
