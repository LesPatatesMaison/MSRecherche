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

    public List<BarDTO> getBarList() throws APIException {
        return concentrateurApiClient.getBarList();
    }

    public BarDTO getBarById(Long id) throws APIException {
        return concentrateurApiClient.getBarById(id);
    }

    public List<BarDTO> findBarByName(String barName) throws APIException {
        return concentrateurApiClient.findBarByName(barName);
    }
}
