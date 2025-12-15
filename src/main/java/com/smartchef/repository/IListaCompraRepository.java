package com.smartchef.repository;

import com.smartchef.model.ListaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IListaCompraRepository extends JpaRepository<ListaCompra, Long> {

    // Listar las listas de compra (valga la redundancia)
    List<ListaCompra> findByUsuarioIdUsuario(Long idUsuario);

    // Solo las listas activas
    List<ListaCompra> findByUsuarioIdUsuarioAndActivaTrue(Long idUsuario);
}
