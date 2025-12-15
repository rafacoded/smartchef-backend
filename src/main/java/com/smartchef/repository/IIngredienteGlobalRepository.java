package com.smartchef.repository;

import com.smartchef.model.IngredienteGlobal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIngredienteGlobalRepository extends JpaRepository<IngredienteGlobal, Long> {
    List<IngredienteGlobal> findByCategoria(String categoria);
    IngredienteGlobal findByNombre(String nombre);
}