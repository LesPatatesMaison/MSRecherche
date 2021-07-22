package org.patatesmaison.msrecherche.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.patatesmaison.msrecherche.util.Logging;

@Getter
@Setter
@NoArgsConstructor
public class BarDTO {

    private int id;

    private String name;

    private String category;

    private String speciality;

    private String phone;

    private String website;

    private String email;

    private String address;

    private String postcode;

    private String city;

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}
