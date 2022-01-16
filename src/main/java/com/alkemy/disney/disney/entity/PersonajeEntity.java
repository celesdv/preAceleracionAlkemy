
package com.alkemy.disney.disney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="personaje")
@Getter
@Setter
public class PersonajeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
        
    private String imagen;
    
    private String nombre;
    
    private Integer edad;
    
    private long peso;
    
    private String historia;
    
    private boolean deleted = Boolean.FALSE;
    
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaEntity> peliculas = new ArrayList<>();


}
