package org.patatesmaison.msrecherche.service;

import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dao.LiaisonBarCocktailRepository;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.patatesmaison.msrecherche.entity.LiaisonBarCocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

    @Autowired
    private LiaisonBarCocktailRepository barCocktailRepository;

   public ArrayList<BarDTO> getBarByCocktailName(String cocktailName) {
       ArrayList<BarDTO> barDTOList = new ArrayList<>();

       List<CocktailDTO> cocktailDTOList = this.getCocktailsByName(cocktailName);
       Integer[] cocktailIds = cocktailDTOList.stream().map(CocktailDTO::getIdDrink).toArray(Integer[]::new);
       List<LiaisonBarCocktail> barCocktail = barCocktailRepository.findByCocktailIds(cocktailIds);
       for(int j = 0; j < barCocktail.size(); j++) {
           BarDTO bar = concentrateurApiClient.call("/etablissement/" + barCocktail.get(j).getBarId(), BarDTO.class);
           barDTOList.add(bar);
       }
       return barDTOList;
    }

    public List<CocktailDTO> getCocktailsByName(String cocktailName)
    {
        CocktailDTO[] response
                = concentrateurApiClient.call("/cocktail/" + cocktailName, CocktailDTO[].class);
        return new ArrayList<>(Arrays.asList(response));
    }
}
