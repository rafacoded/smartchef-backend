package com.smartchef.mapper;

import com.smartchef.dto.ListaItemDTO;
import com.smartchef.model.*;
import org.springframework.stereotype.Component;

@Component
public class ListaItemMapper {

    public ListaItemDTO toDTO(ListaItem item) {
        return new ListaItemDTO(
                item.getIdItem(),
                item.getListaCompra() != null ? item.getListaCompra().getIdLista() : null,
                item.getListaCompra() != null ? item.getListaCompra().getNombreLista() : null,
                item.getIngrediente() != null ? item.getIngrediente().getIdIngrediente() : null,
                item.getIngrediente() != null ? item.getIngrediente().getNombre() : null,
                item.getCantidad(),
                item.getUnidad() != null ? item.getUnidad().name() : null,
                item.isComprado()
        );
    }

    public ListaItem toEntity(ListaItemDTO dto) {
        ListaItem item = new ListaItem();
        item.setIdItem(dto.getIdItem());
        item.setCantidad(dto.getCantidad());
        item.setComprado(dto.isComprado());

        if (dto.getUnidad() != null)
            item.setUnidad(UnidadMedida.valueOf(dto.getUnidad().toUpperCase()));

        if (dto.getIdLista() != null) {
            ListaCompra lista = new ListaCompra();
            lista.setIdLista(dto.getIdLista());
            item.setListaCompra(lista);
        }

        if (dto.getIdIngrediente() != null) {
            IngredienteGlobal ing = new IngredienteGlobal();
            ing.setIdIngrediente(dto.getIdIngrediente());
            item.setIngrediente(ing);
        }

        return item;
    }
}
