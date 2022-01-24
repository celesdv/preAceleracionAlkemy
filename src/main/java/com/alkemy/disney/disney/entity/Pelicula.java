
package com.alkemy.disney.disney.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Pelicula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String titulo;
    
    private String imagen;
    
    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;
    
    private Integer calificacion;

    /*
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;
    
    @Column(name = "genero_id", nullable = false)
    private long generoId;
    */
    
    @ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})    
    @JoinTable(name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajes = new ArrayList<>();

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "genero_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<Genero> generos = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    public void addPersonaje(Personaje personaje) {
        this.personajes.add(personaje);
    }

    public void removePersonaje(Personaje personaje) {
        this.personajes.remove(personaje);
    }

    public void addGenero(Genero genero) {
        this.generos.add(genero);
    }

    public void removeGenero(Genero genero) {
        this.generos.remove(genero);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final Pelicula other = (Pelicula) obj;
        return other.id == this.id;
    }
}
