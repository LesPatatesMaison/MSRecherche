package org.patatesmaison.msrecherche.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.patatesmaison.msrecherche.service.BarCocktailService;
import org.patatesmaison.msrecherche.service.CocktailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cocktail")
@AllArgsConstructor
@Slf4j
public class CocktailController {

    private final CocktailService cocktailService;

    private final BarCocktailService barCocktailService;

    @ApiOperation(value = "Recherche de cocktails par propriété", response = CocktailDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des cocktails correspondant"),
    })
    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CocktailDTO> searchCocktails(@RequestParam Map<String, String> allRequestParams) throws APIException {
        if(allRequestParams.size() > 1) {
            throw new APIException("Cette requête n'accepte qu'un paramètre", HttpStatus.BAD_REQUEST);
        }
        if(allRequestParams.containsKey("name")) {
            return cocktailService.getCocktailsByName(allRequestParams.get("name"));
        }
        if(allRequestParams.containsKey("ingredient")) {
            return cocktailService.getCocktailsByIngredient(allRequestParams.get("ingredient"));
        }
        throw new APIException("Veuillez saisir UN paramètre parmi name et ingredient", HttpStatus.BAD_REQUEST);
    }

    @ApiParam(name = "{barId}", required = true)
    @ApiOperation(value = "Rechercher les cocktails proposés par un bar", response = CocktailDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des cocktails d'un bar (potentiellement vide)"),
    })
    @GetMapping("/bar/{barId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CocktailDTO> getCocktailListByBar(@PathVariable("barId") Long barId) throws APIException {
        return barCocktailService.getCocktailListByBar(barId);
    }
}
