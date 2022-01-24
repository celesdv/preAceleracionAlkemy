package com.alkemy.disney.disney.service.impl;


import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specification.peliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeService personajeService;

    public PeliculaDTO save(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaMapper.peliculaDTO2Entity(peliculaDTO, true);
        Pelicula peliculaSaved = peliculaRepository.save(pelicula);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(peliculaSaved, false);

        return result;
    }

    public List<PeliculaDTO> getAllPeliculas() {
        List<Pelicula> entities = peliculaRepository.findAll();
        List<PeliculaDTO> result = peliculaMapper.peliculaEntity2DTOList(entities, true);

        return result;
    }

    public List<PeliculaBasicDTO> getBasicList() {
        List<Pelicula> peliculasList = peliculaRepository.findAll();
        List<PeliculaBasicDTO> resultDTO = peliculaMapper.entityList2BasicDTO(peliculasList);
        return resultDTO;
    }

    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    public PeliculaDTO modify(Long id, PeliculaDTO peliculaDTO) {
        Pelicula peliculaSaved = this.getById(id);

        peliculaSaved.setImagen(peliculaDTO.getImagen());
        peliculaSaved.setTitulo(peliculaDTO.getTitulo());

        String date = peliculaDTO.getFechaCreacion().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaTransformed = LocalDate.parse(date, formatter);
        peliculaSaved.setFechaCreacion(fechaTransformed);

        peliculaSaved.setCalificacion(peliculaDTO.getCalificacion());

        Pelicula movieEntity = peliculaRepository.save(peliculaSaved);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(movieEntity, false);

        return result;
    }

    public void addPersonaje(Long peliculaId, Long personajeId) {
        Pelicula pelicula = this.getById(peliculaId);
        pelicula.getPersonajes().size();

        Personaje personaje = personajeService.getById(personajeId);
        pelicula.addPersonaje(personaje);
        peliculaRepository.save(pelicula);
    }

    public List<PeliculaDTO> getByFilters(String titulo, Set<Long> genero, String orden) {
        PeliculaFilterDTO movipeliculaFilter = new PeliculaFilterDTO(titulo, genero, orden);
        List<Pelicula> peliculas = peliculaRepository.findAll((Sort) peliculaSpecification.getFiltered(movipeliculaFilter));
        List<PeliculaDTO> result = peliculaMapper.peliculaEntity2DTOList(peliculas, true);
        return result;
    }

    public Pelicula getById(Long id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        if(!pelicula.isPresent()) {
            throw new ParamNotFound("Id pelicula no encontrado");
        }
        return pelicula.get();
    }

    public PeliculaDTO getByDetails(Long id) {
        Pelicula pelicula = this.getById(id);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(pelicula, true);
        return result;
    }

    public void addGenero(Long peliculaId, Long generoId) {

    }

}
