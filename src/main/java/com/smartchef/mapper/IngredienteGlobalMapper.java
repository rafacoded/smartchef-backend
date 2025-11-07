package com.smartchef.mapper;

import com.smartchef.dto.IngredienteGlobalDTO;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.model.UnidadMedida;
import org.springframework.stereotype.Component;

@Component
public class IngredienteGlobalMapper {

    public IngredienteGlobalDTO toDTO(IngredienteGlobal ingrediente) {
        if (ingrediente == null) return null;

        return new IngredienteGlobalDTO(
                ingrediente.getIdIngrediente(),
                ingrediente.getNombre(),
                ingrediente.getCategoria(),
                ingrediente.getUnidadBase() != null ? ingrediente.getUnidadBase().name() : null
        );
    }

    public IngredienteGlobal toEntity(IngredienteGlobalDTO dto) {
        if (dto == null) return null;

        IngredienteGlobal ingrediente = new IngredienteGlobal();
        ingrediente.setIdIngrediente(dto.getIdIngrediente());
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setCategoria(dto.getCategoria());
        if (dto.getUnidadBase() != null) {
            ingrediente.setUnidadBase(UnidadMedida.valueOf(dto.getUnidadBase().toUpperCase()));
        }

        return ingrediente;
    }
}
