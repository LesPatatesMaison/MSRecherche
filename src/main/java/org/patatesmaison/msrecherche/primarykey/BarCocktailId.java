package org.patatesmaison.msrecherche.primarykey;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class BarCocktailId implements Serializable {
    private Long barId;
    private Long cocktailId;

    public BarCocktailId(Long barId, Long cocktailId) {
        this.barId = barId;
        this.cocktailId = cocktailId;
    }

    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of BarCocktailId or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof BarCocktailId)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        BarCocktailId c = (BarCocktailId) o;

        // Compare the data members and return accordingly
        return Long.compare(barId, c.barId) == 0
                && Double.compare(cocktailId, c.cocktailId) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barId, cocktailId);
    }
}
