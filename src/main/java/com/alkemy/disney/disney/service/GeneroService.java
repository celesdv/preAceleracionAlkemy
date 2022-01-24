package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.Genero;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> getAllGeneros();

    GeneroDTO modify(Long id, GeneroDTO dto);

    void delete(Long id);

    Genero getById(Long id);
}
