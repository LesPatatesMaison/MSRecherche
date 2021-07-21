package org.patatesmaison.msrecherche.entity;

import lombok.NoArgsConstructor;
import org.patatesmaison.msrecherche.primarykey.BarCocktailId;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@IdClass(BarCocktailId.class)
public class LiaisonBarCocktail {
    @Id
    private Long barId;

    @Id
    private Long cocktailId;

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    public Long getBarId() {
        return barId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Long getCocktailId() {
        return cocktailId;
    }
}
