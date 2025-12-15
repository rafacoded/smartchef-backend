package com.smartchef.mapper;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.dto.GuardadoRecetaResponseDTO;
import com.smartchef.model.GuardadoReceta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface GuardadoRecetaMapper {

    GuardadoReceta toEntity(GuardadoRecetaDTO guardadoRecetaDTO);

    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "receta.idReceta", target = "idReceta")
    GuardadoRecetaDTO toDTO(GuardadoReceta guardadoReceta);

    GuardadoRecetaResponseDTO toResponseDTO(GuardadoReceta guardado);

}
