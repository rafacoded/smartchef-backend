package com.smartchef.repository;

import com.smartchef.model.ValoracionReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IValoracionRecetaRepository extends JpaRepository<ValoracionReceta, Long> {
    List<ValoracionReceta> findByRecetaIdReceta(Long idReceta);
    List<ValoracionReceta> findByUsuarioIdUsuario(Long idUsuario);
}
