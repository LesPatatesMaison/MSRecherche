package org.patatesmaison.msrecherche.dao;

import org.patatesmaison.msrecherche.entity.LiaisonBarCocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiaisonBarCocktailRepository extends JpaRepository<LiaisonBarCocktail, Integer> {
}
