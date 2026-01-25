package com.smartchef.repository;

import com.smartchef.model.Dificultad;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByCategoriaIgnoreCase(String categoria);

    Optional<Receta> findById(Long idReceta);

    // SEGUNDA REQUEST
    @Query("""
    SELECT DISTINCT r
    FROM Receta r
    JOIN r.preferencias pref
    WHERE (:categoria IS NULL OR LOWER(r.categoria) = LOWER(:categoria))
      AND (:prefList IS NULL OR pref IN :prefList)
""")
    List<Receta> filtrar(
            @Param("categoria") String categoria,
            @Param("prefList") List<PreferenciaAlimentaria> prefList
    );

    // JPQL
    @Query("select r from Receta r where r.dificultad = :dificultad and r.tiempoPreparacion > :tiempoPreparacion")
    List<Receta> buscarPorDificultadAndTiempoPreparacion(String dificultad, Integer tiempoPreparacion);


    // JPA

    List<Receta> findAllByCategoriaAndDificultadEquals(String categoria, Dificultad dificultad);
}

