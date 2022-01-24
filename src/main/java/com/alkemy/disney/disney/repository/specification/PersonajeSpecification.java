package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {
    public Specification<Personaje> getByFilters(PersonajeFilterDTO filterDTO){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filterDTO.getNombre())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filterDTO.getNombre().toLowerCase() + "%"));
            }

            if(filterDTO.getEdad() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filterDTO.getEdad()								)
                );
            }

            if(!CollectionUtils.isEmpty(filterDTO.getPeliculas())) {
                Join<Personaje, Pelicula> join = root.join("characterMovies", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filterDTO.getPeliculas()));
            }

            query.distinct(true);

            String orderByField = "nombre";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
