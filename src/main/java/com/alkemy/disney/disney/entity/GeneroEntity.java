
package com.alkemy.disney.disney.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="genero")
@Getter
@Setter
public class GeneroEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    private String nombre;
    
    private String imagen;
    
}
