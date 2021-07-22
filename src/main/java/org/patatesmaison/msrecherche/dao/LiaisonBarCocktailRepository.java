package org.patatesmaison.msrecherche.dao;

import org.patatesmaison.msrecherche.entity.LiaisonBarCocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LiaisonBarCocktailRepository extends JpaRepository<LiaisonBarCocktail, Integer> {
    List<LiaisonBarCocktail> findByCocktailId(int cocktailId);

    @Query("SELECT bc FROM LiaisonBarCocktail bc WHERE bc.cocktailId IN (?1)")
    List<LiaisonBarCocktail> findByCocktailIds(Integer[] cocktailIds);
}
