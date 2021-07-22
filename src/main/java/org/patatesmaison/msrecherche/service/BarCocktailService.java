package org.patatesmaison.msrecherche.service;

import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarCocktailService {
    @Value("${resource-base-url}")
    private String resourceBaseUrl;

    @Value("${messages.bar.not-found}")
    private String messageBarNotFound;

    @Value("${messages.cocktail.not-found}")
    private String messageCocktailNotFound;

    @Autowired
    private ConcentrateurApiClient concentrateurApiClient;

   public ArrayList<BarDTO> getBarByCocktailName(String cocktailName) {
       List<CocktailDTO> cocktailDTOList = this.getCocktailsIdByName(cocktailName);
       for(int i = 0; i < cocktailDTOList.size(); i++) {
           //cocktailId = cocktailDTOList.get(i).getIdDrink();
       }
       ArrayList<BarDTO> tempRep = new ArrayList<>();
       return tempRep;
    }

    public List<CocktailDTO> getCocktailsIdByName(String cocktailName)
    {
        ResponseEntity<List<CocktailDTO>> response
                = concentrateurApiClient.call("/cocktail/" + cocktailName, List.class);
        return response.getBody() == null ? new ArrayList<>() : response.getBody();
    }
}
