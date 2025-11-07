package com.smartchef.repository;

import com.smartchef.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByCategoria(String categoria);
    List<Receta> findByAutorIdUsuario(Long idUsuario);
}
