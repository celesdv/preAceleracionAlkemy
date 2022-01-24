package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private GeneroMapper generoMapper;

    public Pelicula peliculaDTO2Entity(PeliculaDTO dto, boolean loadPersonaje){
        Pelicula entity = new Pelicula();

        entity.setTitulo(dto.getTitulo());
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());

        String date = dto.getFechaCreacion();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate FechaTransformed = LocalDate.parse(date, formatter);
        entity.setFechaCreacion(FechaTransformed);

        if(loadPersonaje) {
            entity.setPersonajes(personajeMapper.personajeDTO2EntityList(dto.getPersonajes(), false));
            entity.setGeneros(generoMapper.generoDTO2EntityList(dto.getGeneros()));
        }

        return entity;
    }

    public PeliculaDTO peliculaEntity2DTO(Pelicula entity, boolean loadPersonaje){
        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setCalificacion(entity.getCalificacion());

        LocalDate fecha = entity.getFechaCreacion();
        String fechaFormat = fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setFechaCreacion(fechaFormat);

        if(loadPersonaje) {
            dto.setPersonajes(personajeMapper.personajeEntity2DTOList(entity.getPersonajes(), false));
            dto.setGeneros(generoMapper.generoEntity2DTOList(entity.getGeneros()));
        }

        return dto;
    }

    public List<PeliculaDTO> peliculaEntity2DTOList(List<Pelicula> entities, boolean load) {
        List<PeliculaDTO> dtos = new ArrayList();

        for (Pelicula entity : entities){
            dtos.add(peliculaEntity2DTO(entity, load));
        }

        return dtos;
    }

    public List<Pelicula> peliculaDTO2EntityList(List<PeliculaDTO> dtoList, boolean load) {
        List<Pelicula> peliculas = new ArrayList();

        for (PeliculaDTO dto : dtoList){
            peliculas.add(peliculaDTO2Entity(dto, load));
        }

        return peliculas;
    }

    public PeliculaBasicDTO entity2BasicDTO(Pelicula pelicula) {
        PeliculaBasicDTO dto = new PeliculaBasicDTO();

        dto.setImagen(pelicula.getImagen());
        dto.setTitulo(pelicula.getTitulo());

        LocalDate date = pelicula.getFechaCreacion();
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setFechaCreacion(formatDate);

        return dto;
    }

    public List<PeliculaBasicDTO> entityList2BasicDTO(List<Pelicula> entities) {
        List<PeliculaBasicDTO> newList = new ArrayList<>();
        for(Pelicula entity : entities) {
            newList.add(this.entity2BasicDTO(entity));
        }
        return newList;
    }
}
