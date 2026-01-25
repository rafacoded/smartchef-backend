package com.smartchef.repository;

import com.smartchef.model.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface IRecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {
    List<RecetaIngrediente> findByRecetaIdReceta(Long idReceta);

    @Query("""
        SELECT
            i.idIngrediente,
            i.nombre,
            COUNT(DISTINCT r.idReceta)
        FROM RecetaIngrediente ri
        JOIN ri.ingrediente i
        JOIN ri.receta r
        GROUP BY i.idIngrediente, i.nombre
        ORDER BY COUNT(DISTINCT r.idReceta) DESC
    """)
    List<Object[]> topIngredientes(Pageable pageable);
}
