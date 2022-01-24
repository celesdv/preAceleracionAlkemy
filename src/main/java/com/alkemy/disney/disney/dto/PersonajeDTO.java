package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Pelicula;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonajeDTO {

    private long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private long peso;
    private String historia;

    private List<PeliculaDTO> peliculas;
}
