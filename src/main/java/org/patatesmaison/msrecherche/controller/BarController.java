package org.patatesmaison.msrecherche.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.patatesmaison.msrecherche.service.BarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("recherche/bar")
@AllArgsConstructor
@Slf4j
public class BarController {

    private final BarService barService;

    @ApiOperation(value = "Voir la liste des bars", response = BarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des bars trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun bar trouvé")
    })
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<BarDTO> getBarList() throws APIException {
        return barService.getBarList();
    }

}
