package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private long id;
    private String titulo;
    private String imagen;
    private String fechaCreacion;
    private Integer calificacion;

    private List<PersonajeDTO> personajes;
    private List<GeneroDTO> generos;

}
