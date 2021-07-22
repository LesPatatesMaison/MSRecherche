package org.patatesmaison.msrecherche.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dao.LiaisonBarCocktailRepository;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BarService {
    @Autowired
    private final ConcentrateurApiClient concentrateurApiClient;

    @Autowired
    private final LiaisonBarCocktailRepository barCocktailRepository;

    @Autowired
    private final CocktailService cocktailService;

    public List<BarDTO> getBarList() throws APIException {
        return concentrateurApiClient.getBarList();
    }

    public BarDTO getBarById(Long id) throws APIException {
        return concentrateurApiClient.getBarById(id);
    }

    public List<BarDTO> findBarByName(String barName) throws APIException {
        return concentrateurApiClient.findBarByName(barName);
    }

    public ArrayList<BarDTO> getBarListByCocktailName(String cocktailName) {
        ArrayList<BarDTO> barDTOList = new ArrayList<>();

        List<CocktailDTO> cocktailDTOList = cocktailService.getCocktailsByName(cocktailName);
        List<Long> cocktailIds = cocktailDTOList.stream().map(CocktailDTO::getIdDrink).collect(Collectors.toList());
        List<Long> barCocktailIds = barCocktailRepository.findBarIdsByCocktailIds(cocktailIds);
        for (Long barCocktailId : barCocktailIds) {
            try {
                BarDTO bar = concentrateurApiClient.getBarById(barCocktailId);
                barDTOList.add(bar);
            } catch(RestClientException e) {
                log.warn("L'établissement d'id {} n'a pas pu être trouvé", barCocktailId);
            }
        }
        return barDTOList;
    }

}
