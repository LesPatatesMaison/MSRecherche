package org.patatesmaison.msrecherche.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.patatesmaison.msrecherche.service.BarCocktailService;
import org.patatesmaison.msrecherche.service.BarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bar")
@AllArgsConstructor
@Slf4j
public class BarController {

    private final BarService barService;

    private final BarCocktailService barCocktailService;

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

    @ApiParam(name = "{id}", required = true)
    @ApiOperation(value = "Voir un bar", response = BarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar trouvé"),
            @ApiResponse(responseCode = "404", description = "Bar non trouvé")
    })
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public BarDTO getBarById(@PathVariable("id") Long id) throws APIException {
        return barService.getBarById(id);
    }

    @ApiOperation(value = "Recherche de bar par nom ou par cocktail", response = BarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar(s) trouvé(s)"),
            @ApiResponse(responseCode = "404", description = "Bar non trouvé")
    })
    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BarDTO> findBarByName(@RequestParam Map<String, String> allRequestParams) throws APIException {
        if(allRequestParams.size() > 1) {
            throw new APIException("Cette requête n'accepte qu'un paramètre", HttpStatus.BAD_REQUEST);
        }
        if(allRequestParams.containsKey("name")) {
            return barService.findBarByName(allRequestParams.get("name"));
        }
        if(allRequestParams.containsKey("cocktail")) {
            return barCocktailService.getBarListByCocktailName(allRequestParams.get("cocktail"));
        }
        if(allRequestParams.containsKey("cocktailId")) {
            return barCocktailService.getBarListByCocktailId(allRequestParams.get("cocktailId"));
        }
        throw new APIException("Veuillez saisir UN paramètre parmi name et cocktail", HttpStatus.BAD_REQUEST);
    }

}
