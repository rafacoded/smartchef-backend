package com.smartchef.mapper;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UsuarioMapper {

    UsuarioResponseDTO toResponseDTO(Usuario u);

    Usuario toEntity(UsuarioDTO dto);

    void updateFromDTO(UsuarioDTO dto, @MappingTarget Usuario entity);

}
