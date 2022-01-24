package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.Genero;
import com.alkemy.disney.disney.entity.Pelicula;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class peliculaSpecification {
    public static Specification<Pelicula> getFiltered(PeliculaFilterDTO peliculaFilters) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Name ==
            if(StringUtils.hasLength(peliculaFilters.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + peliculaFilters.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            // == Genre ==
            if(!CollectionUtils.isEmpty(peliculaFilters.getGenero())) {
                Join<Pelicula, Genero> join = root.join("peliculaGenero", JoinType.INNER);
                Expression<String> generosId = join.get("id");
                predicates.add(generosId.in(peliculaFilters.getGenero()));
            }

            query.distinct(true);

            // == Order ==
            String orderByField = "titulo";
            query.orderBy(
                    peliculaFilters.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

