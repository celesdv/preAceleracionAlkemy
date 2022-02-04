package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.Genero;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneroMapper {

    public Genero generoDTO2Entity(GeneroDTO dto){
        Genero entity = new Genero();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public GeneroDTO generoEntity2DTO(Genero entity){
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<GeneroDTO> generoEntity2DTOList(List<Genero> entities) {
        return entities.stream().map(entity -> generoEntity2DTO(entity) ).collect(Collectors.toList());
    }

    public List<Genero> generoDTO2EntityList(List<GeneroDTO> dtoList) {
        return dtoList.stream().map(dto -> generoDTO2Entity(dto)).collect(Collectors.toList());
    }
}
