package com.smartchef.mapper;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.dto.ListaCompraResponseDTO;
import com.smartchef.model.ListaCompra;
import com.smartchef.model.ListaItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ListaCompraMapper {

    ListaCompraDTO toDTO(ListaCompra lc);

    ListaCompra toEntity(ListaCompraDTO dto);

    ListaCompraResponseDTO toResponseDTO(ListaCompra lc, List<ListaItem> items);
}
