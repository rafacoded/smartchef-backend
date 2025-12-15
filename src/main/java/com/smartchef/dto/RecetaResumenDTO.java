package com.smartchef.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RecetaResumenDTO {
    private Long idReceta;
    private String titulo;
    private String dificultad;
    private Integer tiempoPreparacion;
}

