package org.patatesmaison.msrecherche.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dao.LiaisonBarCocktailRepository;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BarCocktailService {
    @Autowired
    private final ConcentrateurApiClient concentrateurApiClient;

    @Autowired
    private final LiaisonBarCocktailRepository barCocktailRepository;

    @Autowired
    private final CocktailService cocktailService;

    @Autowired
    private final BarService barService;

    public ArrayList<BarDTO> getBarListByCocktailName(String cocktailName) {
        ArrayList<BarDTO> barDTOList = new ArrayList<>();

        List<CocktailDTO> cocktailDTOList = cocktailService.getCocktailsByName(cocktailName);
        List<Long> cocktailIds = cocktailDTOList.stream().map(CocktailDTO::getIdDrink).collect(Collectors.toList());
        List<Long> barIds = barCocktailRepository.findBarIdsByCocktailIds(cocktailIds);
        for (Long barId : barIds) {
            try {
                BarDTO bar = concentrateurApiClient.getBarById(barId);
                barDTOList.add(bar);
            } catch(RestClientException e) {
                log.warn("L'établissement d'id {} n'a pas pu être trouvé", barId);
            }
        }
        return barDTOList;
    }

    public ArrayList<CocktailDTO> getCocktailListByBar(Long barId) throws APIException {
        ArrayList<CocktailDTO> cocktailDTOList = new ArrayList<>();

        try {
            BarDTO bar = barService.getBarById(barId);
            List<Long> cocktailIds = barCocktailRepository.findCocktailIdsByBarId(bar.getId());
            for (Long cocktailId : cocktailIds) {
                try {
                    CocktailDTO cocktail = concentrateurApiClient.getCocktailById(cocktailId);
                    cocktailDTOList.add(cocktail);
                } catch(RestClientException e) {
                    log.warn("Le cocktail d'id {} n'a pas pu être trouvé", cocktailId);
                }
            }
        } catch (APIException e) {
            throw new APIException("Bar inconnu", HttpStatus.NOT_FOUND);
        }

        return cocktailDTOList;
    }
}
