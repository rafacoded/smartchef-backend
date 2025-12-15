package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredienteUsoDTO {
    private Long idIngrediente;
    private String nombre;
    private Long numeroRecetas;
}
