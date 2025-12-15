package com.smartchef.repository;

import com.smartchef.model.GuardadoReceta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGuardadoRecetaRepository extends JpaRepository<GuardadoReceta, Long> {
    List<GuardadoReceta> findByUsuarioIdUsuario(Long idUsuario);

    boolean existsByUsuarioIdUsuarioAndRecetaIdReceta(Long idUsuario, Long recetaId);

    @Query("""
        select gr.receta.idReceta, count(gr.idGuardado)
        from GuardadoReceta gr
        group by gr.receta.idReceta
        order by count(gr.idGuardado) desc
    """)
    List<Object[]> recetaMasGuardada(Pageable pageable);

    @Query("""
        select gr.usuario.idUsuario, gr.usuario.nombre, gr.receta.idReceta, gr.receta.titulo
        from GuardadoReceta gr
        where gr.receta.idReceta = :idReceta
    """)
    List<Object[]> usuariosPorReceta(@Param("idReceta") Long idReceta);


}
