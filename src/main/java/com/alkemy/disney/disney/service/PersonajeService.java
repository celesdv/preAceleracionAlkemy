package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Personaje;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    List<PersonajeDTO> getAll();

    List<PersonajeBasicDTO> getPersonajeBasicList();

    PersonajeDTO modify(Long id, PersonajeDTO personajeDTO);

    PersonajeDTO save(PersonajeDTO dto);

    Personaje getById(Long id);

    void delete(Long id);

    List<PersonajeDTO> getByFilters(String nombre, Integer edad, Set<Long> peliculas, String orden);

    PersonajeDTO getDetailById(Long id);

    void addPelicula(Long id, Long idPelicula);

    void removeMovie(Long id, Long idPelicula);
}
