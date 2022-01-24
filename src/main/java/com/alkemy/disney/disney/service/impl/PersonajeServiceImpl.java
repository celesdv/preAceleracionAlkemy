package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specification.PersonajeSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    public List<PersonajeDTO> getAll() {
        List<Personaje> entities = personajeRepository.findAll();
        List<PersonajeDTO> dtoList = personajeMapper.personajeEntity2DTOList(entities, true);

        return dtoList;
    }

    public List<PersonajeBasicDTO> getPersonajeBasicList() {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeBasicDTO> resultDTO = personajeMapper.basicEntity2DTOBasicList(personajes);

        return resultDTO;
    }

    public PersonajeDTO modify(Long id, PersonajeDTO personajeDTO) {
        Personaje entity = this.getById(id);

        entity.setNombre(personajeDTO.getNombre());
        entity.setImagen(personajeDTO.getImagen());
        entity.setEdad(personajeDTO.getEdad());
        entity.setPeso(personajeDTO.getPeso());
        entity.setHistoria(personajeDTO.getHistoria());

        entity.setPeliculas(peliculaMapper.peliculaDTO2EntityList(personajeDTO.getPeliculas(), false));

        Personaje personajeEdited = personajeRepository.save(entity);

        PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(personajeEdited, false);

        return savedDTO;
    }

    public PersonajeDTO save(PersonajeDTO dto) {
        Personaje entity = new Personaje();

        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        Personaje personajeSaved = personajeRepository.save(entity);
        PersonajeDTO savedDTO = personajeMapper.personajeEntity2DTO(personajeSaved, false);

        return savedDTO;
    }

    public Personaje getById(Long id) {
        Optional<Personaje> entity = personajeRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("Id PErsonaje no encontrado");
        }
        return entity.get();
    }

    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }

    public List<PersonajeDTO> getByFilters(String nombre, Integer edad, Set<Long> peliculas, String orden) {
        PersonajeFilterDTO personajeFilter = new PersonajeFilterDTO(nombre, edad, peliculas, orden);

        List<Personaje> entities = personajeRepository.findAll((Sort) personajeSpecification.getByFilters(personajeFilter));
        List<PersonajeDTO> result = personajeMapper.personajeEntity2DTOList(entities,true);

        return result;
    }

    public PersonajeDTO getDetailById(Long id) {
        Personaje personaje = this.getById(id);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personaje, true);

        return result;
    }

    public void addPelicula(Long id, Long idPelicula) {
        Personaje entity = personajeRepository.getById(id);

        entity.getPeliculas().size();
        Pelicula pelicula = this.peliculaService.getById(idPelicula);
        entity.addPelicula(pelicula);

        this.personajeRepository.save(entity);
    }

    public void removeMovie(Long id, Long idPelicula) {
        Personaje entity = personajeRepository.getById(id);

        entity.getPeliculas().size();
        Pelicula pelicula = this.peliculaService.getById(idPelicula);
        entity.removePelicula(pelicula);

        this.personajeRepository.save(entity);
    }
}
