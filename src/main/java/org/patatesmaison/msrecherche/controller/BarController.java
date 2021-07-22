package org.patatesmaison.msrecherche.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.dto.BarDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.patatesmaison.msrecherche.service.BarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("bar")
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

    @ApiParam(name = "{cocktailName}", required = true)
    @ApiOperation(value = "Rec hercher les bars proposant un cocktail", response = BarDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des bars (potentiellement vide)"),
    })
    @GetMapping("/cocktail/{cocktailName}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BarDTO> getBarListByCocktailName(@PathVariable("cocktailName") String cocktailName) throws APIException {
        return barService.getBarListByCocktailName(cocktailName);
    }

    @ApiOperation(value = "Recherche de bar par nom", response = BarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bar(s) trouvé(s)"),
            @ApiResponse(responseCode = "404", description = "Bar non trouvé")
    })
    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BarDTO> findBarByName(@RequestParam(value = "name", required = true) String name) throws APIException {
        return barService.findBarByName(name);
    }

}
