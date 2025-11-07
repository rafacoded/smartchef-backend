package com.smartchef.repository;

import com.smartchef.model.HistorialCocina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialCocinaRepository extends JpaRepository<HistorialCocina, Long> {
    List<HistorialCocina> findByUsuarioIdUsuarioOrderByFechaRealizacionDesc(Long idUsuario);
    List<HistorialCocina> findByUsuarioIdUsuarioAndFechaRealizacion(Long idUsuario, LocalDate fecha);
}
