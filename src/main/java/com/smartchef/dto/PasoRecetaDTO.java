package com.smartchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasoRecetaDTO {
    @NotNull(message = "El orden del paso es obligatorio")
    private Integer orden;

    @NotBlank(message = "La descripción del paso no puede estar vacía")
    private String descripcion;
}
