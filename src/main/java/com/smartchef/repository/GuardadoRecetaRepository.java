package com.smartchef.repository;

import com.smartchef.model.GuardadoReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuardadoRecetaRepository extends JpaRepository<GuardadoReceta, Long> {
    List<GuardadoReceta> findByUsuarioIdUsuario(Long idUsuario);
}
