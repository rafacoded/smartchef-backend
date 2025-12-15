package com.smartchef.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioUsuarioDTO {

    private Long idInventario;

    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "La cantidad debe ser mayor que 0")
    private Double cantidad;

    @NotBlank(message = "La unidad es obligatoria")
    private String unidad;

    private LocalDateTime fechaActualizacion;

    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El ingrediente es obligatorio")
    private Long idIngrediente;
}
