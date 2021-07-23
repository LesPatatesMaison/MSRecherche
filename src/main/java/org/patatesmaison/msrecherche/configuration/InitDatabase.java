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
        Long[] cocktailId = {17207L, 11007L, 11118L, 16158L, 11113L, 15182L, 12754L, 11000L, 15841L, 11006L, 11064L, 12316L, 11202L, 13206L, 11102L, 12528L, 14356L, 15403L, 13621L, 17168L, 15941L, 11728L, 14167L, 17181L, 14157L, 15106L, 17215L, 17218L, 17253L, 17225L, 13128L, 15266L, 17255L, 17187L, 17840L, 178310L, 11320L, 11375L, 11408L, 11938L};

        for (int i = 0; i < cocktailId.length; i++) {
            int randomNbBar = (int) Math.ceil((Math.random() * 15)); // un cocktail peut se retrouver dans 1 à 15 bars

            Long previousBarId = 0L;
            for (int j = 0; j < randomNbBar; j++) {
                Long randomBar = (long) Math.ceil((Math.random() * 40)); // choix d'un bar parmi 40
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
