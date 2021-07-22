package org.patatesmaison.msrecherche.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CocktailService {
    private final ConcentrateurApiClient concentrateurApiClient;

    public List<CocktailDTO> getCocktailsByName(String cocktailName)
    {
        return concentrateurApiClient.findCocktailsByName(cocktailName);
    }
}
