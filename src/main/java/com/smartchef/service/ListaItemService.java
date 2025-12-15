package com.smartchef.service;

import com.smartchef.dto.ListaItemDTO;
import com.smartchef.mapper.ListaItemMapper;
import com.smartchef.model.ListaItem;
import com.smartchef.repository.IListaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaItemService {

    private final IListaItemRepository listaItemRepository;
    private final ListaItemMapper listaItemMapper;

    public ListaItemDTO crearListaItem(ListaItemDTO item) {
        ListaItem LItem = listaItemMapper.toEntity(item);
        ListaItem guardado =  listaItemRepository.save(LItem);
        return listaItemMapper.toDTO(guardado);
    }

    public List<ListaItem> guardarItems(List<ListaItem> items) {
        return listaItemRepository.saveAll(items);
    }

    public List<ListaItemDTO> listarPorListaCompra(Long idLista) {
        return listaItemRepository.findByListaCompraIdLista(idLista)
                .stream()
                .map(listaItemMapper::toDTO)
                .toList();
    }

    public ListaItemDTO actualizarItem(Long id, ListaItemDTO dto) {
        ListaItem existente = listaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        listaItemMapper.updateEntityFromDTO(dto, existente);

        ListaItem actualizado = listaItemRepository.save(existente);
        return listaItemMapper.toDTO(actualizado);
    }


    public void eliminar(Long idItem) {
        listaItemRepository.deleteById(idItem);
    }
}
