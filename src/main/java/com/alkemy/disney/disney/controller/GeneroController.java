package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){

        GeneroDTO generoGuardado = generoService.save(genero);

        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> modify(@PathVariable Long id, @RequestBody GeneroDTO dto){
        GeneroDTO generoEdited = generoService.modify(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generoEdited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        generoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
