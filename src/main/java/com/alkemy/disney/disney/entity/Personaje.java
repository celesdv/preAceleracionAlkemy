
package com.alkemy.disney.disney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Personaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        
    private String imagen;
    
    private String nombre;
    
    private Integer edad;
    
    private long peso;
    
    private String historia;
    
    private boolean deleted = Boolean.FALSE;
    
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();

    public void addPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }

    public void removePelicula(Pelicula pelicula) {
        this.peliculas.remove(pelicula);
    }

}
