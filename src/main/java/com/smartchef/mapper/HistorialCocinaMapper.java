package com.smartchef.mapper;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.dto.HistorialCocinaResponseDTO;
import com.smartchef.model.HistorialCocina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface HistorialCocinaMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta", target = "receta")
    HistorialCocinaResponseDTO toResponseDTO(HistorialCocina historialCocina);
}

