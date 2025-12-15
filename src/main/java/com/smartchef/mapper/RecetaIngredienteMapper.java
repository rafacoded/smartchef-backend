package com.smartchef.mapper;

import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.model.RecetaIngrediente;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface RecetaIngredienteMapper {

    RecetaIngrediente toEntity(RecetaIngredienteDTO recetaIngredienteDTO);

    RecetaIngredienteDTO toDTO(RecetaIngrediente recetaIngrediente);


}
