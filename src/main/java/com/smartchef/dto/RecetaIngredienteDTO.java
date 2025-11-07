package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaIngredienteDTO {
    private Long idRecetaIngrediente;
    private Long idReceta;
    private String tituloReceta;
    private Long idIngrediente;
    private String nombreIngrediente;
    private Double cantidad;
    private String unidad; // Representación textual del Enum
}
