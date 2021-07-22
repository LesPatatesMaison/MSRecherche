package org.patatesmaison.msrecherche.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.client.ConcentrateurApiClient;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BarService {

    private final ConcentrateurApiClient concentrateurApiClient;

    public List<BarDTO> getBarList() throws APIException {
        return concentrateurApiClient.getBarList();
    }

}
