package org.patatesmaison.msrecherche.configuration;

import org.patatesmaison.msrecherche.dao.LiaisonBarCocktailRepository;
import org.patatesmaison.msrecherche.entity.LiaisonBarCocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase implements ApplicationRunner {
    private final LiaisonBarCocktailRepository repository;

    @Autowired
    public InitDatabase(LiaisonBarCocktailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Integer[] cocktailId = {11007, 11118, 16158, 11113, 15182, 12754, 11000, 15841, 11006, 11064, 12316, 11202, 13206};

        for (int i = 0; i < cocktailId.length; i++) {
            int randomNbBar = (int) Math.ceil((Math.random() * 10)); // un cocktail peut se retrouver dans 1 à 10 bars

            int previousBarId = 0;
            for (int j = 0; j < randomNbBar; j++) {
                int randomBar = (int) Math.ceil((Math.random() * 50)); // choix d'un bar parmi 50
                if(randomBar != previousBarId)
                {
                    previousBarId = randomBar;
                    LiaisonBarCocktail assoc = new LiaisonBarCocktail(randomBar, cocktailId[i]);
                    repository.save(assoc);
                }
            }
        }
    }
}
