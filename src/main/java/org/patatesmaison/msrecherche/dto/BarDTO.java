package org.patatesmaison.msrecherche.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarDTO {
    private int id;
    private String name;
    private String category;
    private String speciality;
}
