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
        Long[] cocktailId = {17207L, 11007L, 11118L, 16158L, 11113L, 15182L, 12754L, 11000L, 15841L, 11006L, 11064L, 12316L, 11202L, 13206L};

        for (int i = 0; i < cocktailId.length; i++) {
            int randomNbBar = (int) Math.ceil((Math.random() * 10)); // un cocktail peut se retrouver dans 1 à 10 bars

            Long previousBarId = 0L;
            for (int j = 0; j < randomNbBar; j++) {
                Long randomBar = (long) Math.ceil((Math.random() * 50)); // choix d'un bar parmi 50
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
