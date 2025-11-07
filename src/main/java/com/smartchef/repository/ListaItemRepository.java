package com.smartchef.repository;

import com.smartchef.model.ListaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaItemRepository extends JpaRepository<ListaItem, Long> {
    List<ListaItem> findByListaCompraIdLista(Long idLista);
}
