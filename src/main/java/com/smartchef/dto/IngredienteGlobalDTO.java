package com.smartchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteGlobalDTO {

    private Long idIngrediente;

    @NotBlank(message = "El nombre del ingrediente es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La categoría del ingrediente es obligatoria")
    @Size(max = 100, message = "La categoría no puede superar los 100 caracteres")
    private String categoria;

    @NotBlank(message = "La unidad base es obligatoria")
    @Pattern(
            regexp = "GRAMO|MILILITRO|UNIDAD|CUCHARADA|TAZA",
            message = "Unidad base no válida."
    )
    private String unidadBase;

}
