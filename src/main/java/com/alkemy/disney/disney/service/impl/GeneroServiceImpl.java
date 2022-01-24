package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.Genero;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDTO save(GeneroDTO dto){
        Genero entity = generoMapper.generoDTO2Entity(dto);
        Genero entitySaved = generoRepository.save(entity);
        GeneroDTO result = generoMapper.generoEntity2DTO(entitySaved);

        return result;
    }

    public List<GeneroDTO> getAllGeneros() {
        List<Genero> entities= generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoEntity2DTOList(entities);
        return result;
    }

    public GeneroDTO modify(Long id, GeneroDTO dto) {
        Genero generoSaved = this.getById(id);
        generoSaved.setNombre(dto.getNombre());
        Genero generoEdited = generoRepository.save(generoSaved);
        GeneroDTO result = generoMapper.generoEntity2DTO(generoEdited);

        return result;
    }

    public void delete(Long id) {
        generoRepository.deleteById(id);
    }

    public Genero getById(Long id) {
        Optional<Genero> result = generoRepository.findById(id);
        if(!result.isPresent()) {
            throw new ParamNotFound("Id genero no encontrado");
        }

        return result.get();

    }
}
