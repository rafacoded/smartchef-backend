package com.smartchef.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaIngredienteDTO {

    private Long idRecetaIngrediente;

    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "La cantidad debe ser mayor que 0")
    private Double cantidad;

    @NotBlank(message = "La unidad es obligatoria")
    private String unidad;

    @NotNull(message = "Debe indicarse la receta")
    private Long idReceta;

    @NotNull(message = "Debe indicarse el ingrediente")
    private Long idIngrediente;
}
