package com.smartchef.mapper;

import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.model.*;
import org.springframework.stereotype.Component;

@Component
public class RecetaIngredienteMapper {

    public RecetaIngredienteDTO toDTO(RecetaIngrediente ri) {
        if (ri == null) return null;

        return new RecetaIngredienteDTO(
                ri.getIdRecetaIngrediente(),
                ri.getReceta() != null ? ri.getReceta().getIdReceta() : null,
                ri.getReceta() != null ? ri.getReceta().getTitulo() : null,
                ri.getIngrediente() != null ? ri.getIngrediente().getIdIngrediente() : null,
                ri.getIngrediente() != null ? ri.getIngrediente().getNombre() : null,
                ri.getCantidad(),
                ri.getUnidad() != null ? ri.getUnidad().name() : null
        );
    }

    public RecetaIngrediente toEntity(RecetaIngredienteDTO dto) {
        if (dto == null) return null;

        RecetaIngrediente ri = new RecetaIngrediente();
        ri.setIdRecetaIngrediente(dto.getIdRecetaIngrediente());
        ri.setCantidad(dto.getCantidad());

        if (dto.getUnidad() != null) {
            ri.setUnidad(UnidadMedida.valueOf(dto.getUnidad().toUpperCase()));
        }

        if (dto.getIdReceta() != null) {
            Receta receta = new Receta();
            receta.setIdReceta(dto.getIdReceta());
            ri.setReceta(receta);
        }

        if (dto.getIdIngrediente() != null) {
            IngredienteGlobal ingrediente = new IngredienteGlobal();
            ingrediente.setIdIngrediente(dto.getIdIngrediente());
            ri.setIngrediente(ingrediente);
        }

        return ri;
    }
}
