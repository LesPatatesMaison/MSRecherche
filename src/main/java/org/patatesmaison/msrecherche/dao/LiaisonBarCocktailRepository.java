package org.patatesmaison.msrecherche.dao;

import org.patatesmaison.msrecherche.entity.LiaisonBarCocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LiaisonBarCocktailRepository extends JpaRepository<LiaisonBarCocktail, Integer> {
    List<LiaisonBarCocktail> findByCocktailId(Long cocktailId);

    @Query("SELECT DISTINCT bc.barId FROM LiaisonBarCocktail bc WHERE bc.cocktailId IN (?1)")
    List<Long> findBarIdsByCocktailIds(List<Long> cocktailIds);

    @Query("SELECT bc.cocktailId FROM LiaisonBarCocktail bc WHERE bc.barId = ?1")
    List<Long> findCocktailIdsByBarId(Long barId);
}
