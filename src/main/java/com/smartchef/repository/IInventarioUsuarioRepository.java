package com.smartchef.repository;

import com.smartchef.model.InventarioUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventarioUsuarioRepository extends JpaRepository<InventarioUsuario, Long> {
    List<InventarioUsuario> findByUsuarioIdUsuario(Long idUsuario);
}
