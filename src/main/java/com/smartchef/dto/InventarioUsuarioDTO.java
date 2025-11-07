package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioUsuarioDTO {
    private Long idInventario;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idIngrediente;
    private String nombreIngrediente;
    private Double cantidad;
    private String unidad;
    private LocalDateTime fechaActualizacion;
}
