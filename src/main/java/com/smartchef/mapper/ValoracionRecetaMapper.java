package com.smartchef.mapper;

import com.smartchef.dto.ValoracionRecetaDTO;
import com.smartchef.model.ValoracionReceta;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ValoracionRecetaMapper {

    ValoracionReceta toEntity(ValoracionRecetaDTO valoracionRecetaDTO);

    ValoracionRecetaDTO toDTO(ValoracionReceta valoracionReceta);

}
