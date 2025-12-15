package com.smartchef.repository;

import com.smartchef.model.ColeccionReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IColeccionRecetaRepository extends JpaRepository<ColeccionReceta, Long> {
    List<ColeccionReceta> findByUsuarioIdUsuario(Long idUsuario);
}
