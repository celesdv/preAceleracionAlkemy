package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeFilterDTO {

    private String nombre;
    private Integer edad;
    private Set<Long> peliculas;
    private String orden;

    public boolean isASC() {

        return orden.compareToIgnoreCase("ASC") == 0 ;
    }

    public boolean isDESC() {
        return orden.compareToIgnoreCase("DESC") == 0 ;
    }
}
