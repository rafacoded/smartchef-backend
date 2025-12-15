package com.smartchef.mapper;

import com.smartchef.dto.ListaItemDTO;
import com.smartchef.model.ListaItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface ListaItemMapper {

    ListaItem toEntity(ListaItemDTO dto);

    ListaItemDTO toDTO(ListaItem l);

    void updateEntityFromDTO(ListaItemDTO dto, @MappingTarget ListaItem entity);


}
