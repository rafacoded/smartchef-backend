package com.smartchef.mapper;

import com.smartchef.dto.IngredienteGlobalDTO;
import com.smartchef.model.IngredienteGlobal;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface IngredienteGlobalMapper {

    IngredienteGlobal toEntity(IngredienteGlobalDTO ingredienteGlobalDTO);

    IngredienteGlobalDTO toDTO(IngredienteGlobal ingredienteGlobal);
}
