package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteGlobalDTO {
    private Long idIngrediente;
    private String nombre;
    private String categoria;
    private String unidadBase;
}
