package com.smartchef.repository;

import com.smartchef.model.HistorialCocina;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IHistorialCocinaRepository extends JpaRepository<HistorialCocina, Long> {

    List<HistorialCocina> findByUsuarioIdUsuarioOrderByFechaRealizacionDesc(Long idUsuario);

    List<HistorialCocina> findByUsuarioIdUsuarioAndFechaRealizacion(Long idUsuario, LocalDate fecha);

    boolean existsByUsuarioAndRecetaAndFechaRealizacion(Usuario usuario, Receta receta, LocalDate fecha);

    @Query("""
        SELECT h FROM HistorialCocina h
        WHERE h.usuario.idUsuario = :idUsuario
        AND h.fechaRealizacion BETWEEN :inicio AND :fin
        ORDER BY h.fechaRealizacion DESC
    """)
    List<HistorialCocina> findHistorialSemanal(
            @Param("idUsuario") Long idUsuario,
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

}
