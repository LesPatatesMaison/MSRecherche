package org.patatesmaison.msrecherche.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.service.CocktailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recherche/cocktail")
@AllArgsConstructor
@Slf4j
public class CocktailController {

    private final CocktailService cocktailService;



}
