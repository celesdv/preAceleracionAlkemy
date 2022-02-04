package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public Personaje personajeDTO2Entity(PersonajeDTO dto){
        Personaje entity = new Personaje();

        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());

        return entity;
    }

    public PersonajeDTO personajeEntity2DTO(Personaje entity, boolean loadPelicula){
        PersonajeDTO dto = new PersonajeDTO();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        dto.setEdad(entity.getEdad());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());

        if(loadPelicula) {
            List<PeliculaDTO> dtoList = new ArrayList<>();
            for (Pelicula pelicula : entity.getPeliculas()) {
                dtoList.add(peliculaMapper.peliculaEntity2DTO(pelicula, false));
                dto.setPeliculas(dtoList);
            }

        }

        return dto;
    }

    public List<PersonajeDTO> personajeEntity2DTOList(List<Personaje> entities, boolean load) {
        return entities.stream().map(entity -> personajeEntity2DTO(entity, load) ).collect(Collectors.toList());
    }

    public List<Personaje> personajeDTO2EntityList(List<PersonajeDTO> dtoList, boolean load) {
        return dtoList.stream().map(dto -> personajeDTO2Entity(dto)).collect(Collectors.toList());
    }

    public List<PersonajeBasicDTO> basicEntity2DTOBasicList(Collection<Personaje> entities) {
        List<PersonajeBasicDTO> basicList = new ArrayList<>();
        PersonajeBasicDTO dtoBasic;

        for (Personaje entity: entities) {
            dtoBasic = new PersonajeBasicDTO();

            dtoBasic.setId(entity.getId());
            dtoBasic.setNombre(entity.getNombre());
            dtoBasic.setImagen(entity.getImagen());

            basicList.add(dtoBasic);
        }

        return basicList;
    }

    private PersonajeBasicDTO personajeEntity2BasicDTO(Personaje personaje) {
        PersonajeBasicDTO dto = new PersonajeBasicDTO();

        dto.setImagen(personaje.getImagen());
        dto.setNombre(personaje.getNombre());

        return dto;
    }
}
