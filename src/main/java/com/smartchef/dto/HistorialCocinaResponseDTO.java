package com.smartchef.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class HistorialCocinaResponseDTO {

    private Long idHistorial;
    private LocalDate fechaRealizacion;
    private String comentario;
    private Integer valoracionPersonal;

    private UsuarioResumenDTO usuario;
    private RecetaResumenDTO receta;
}

