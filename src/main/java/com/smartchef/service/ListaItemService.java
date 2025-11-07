package com.smartchef.service;

import com.smartchef.model.ListaItem;
import com.smartchef.repository.ListaItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaItemService {

    private final ListaItemRepository listaItemRepository;

    public ListaItemService(ListaItemRepository listaItemRepository) {
        this.listaItemRepository = listaItemRepository;
    }

    public List<ListaItem> listarPorLista(Long idLista) {
        return listaItemRepository.findByListaCompraIdLista(idLista);
    }

    public ListaItem guardar(ListaItem item) {
        return listaItemRepository.save(item);
    }

    public void eliminar(Long idItem) {
        listaItemRepository.deleteById(idItem);
    }
}
