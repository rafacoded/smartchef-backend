package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDTO {
    private Long idReceta;
    private String titulo;
    private String descripcion;
    private String pasos;
    private Integer tiempoPreparacion;
    private String dificultad;
    private String categoria;
    private String imagen;
    private LocalDateTime fechaCreacion;
    private Long idAutor;       // solo el id del usuario autor
}
