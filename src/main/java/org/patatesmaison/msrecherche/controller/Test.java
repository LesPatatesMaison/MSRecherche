package org.patatesmaison.msrecherche.controller;


import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msrecherche.dto.CocktailDTO;
import org.patatesmaison.msrecherche.exception.APIException;
import org.patatesmaison.msrecherche.exception.ErrorMessage;
import org.patatesmaison.msrecherche.service.BarCocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {

    @Autowired
    private BarCocktailService barCocktailService;

    @PostConstruct
    public void logEnvSpecificValue() {
//        log.warn("---------------------------------------------");
//        log.warn("------------------------------ test : {}", test);
//        log.warn("---------------------------------------------");
    }


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<CocktailDTO> test() {
        log.warn("-------------- Test Recherche OK");
        return new ArrayList<>();
//        return barCocktailService.testAPI();
    }




    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorMessage> handleAPIException(APIException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getHttpStatus());
    }
}
