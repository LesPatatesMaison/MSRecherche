package org.patatesmaison.msrecherche.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.patatesmaison.msrecherche.primarykey.BarCocktailId;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@IdClass(BarCocktailId.class)
@Getter
@Setter
public class LiaisonBarCocktail {
    @Id
    private Integer barId;

    @Id
    private Integer cocktailId;

    public LiaisonBarCocktail(Integer barId, Integer cocktailId)
    {
        this.barId = barId;
        this.cocktailId = cocktailId;
    }
}
