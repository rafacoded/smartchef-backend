package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialCocinaDTO {
    private Long idHistorial;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idReceta;
    private String tituloReceta;
    private LocalDate fechaRealizacion;
    private String comentario;
    private Integer valoracionPersonal;
}
