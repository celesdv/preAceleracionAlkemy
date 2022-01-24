package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.Pelicula;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO peliculaDTO);

    List<PeliculaDTO> getAllPeliculas();

    List<PeliculaBasicDTO> getBasicList();

    void delete(Long id);

    PeliculaDTO modify(Long id, PeliculaDTO peliculaDTO);

    void addPersonaje(Long peliculaId, Long personajeId);

    List<PeliculaDTO> getByFilters(String titulo, Set<Long> genero, String orden);

    Pelicula getById(Long id);

    PeliculaDTO getByDetails(Long id);

    void addGenero(Long peliculaId, Long generoId);
}
