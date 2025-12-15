package com.smartchef.mapper;

import com.smartchef.dto.InventarioUsuarioDTO;
import com.smartchef.model.InventarioUsuario;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface InventarioUsuarioMapper {

    InventarioUsuario toEntity(InventarioUsuarioDTO inventarioUsuarioDTO);

    InventarioUsuarioDTO toDTO(InventarioUsuario inventarioUsuario);
}
