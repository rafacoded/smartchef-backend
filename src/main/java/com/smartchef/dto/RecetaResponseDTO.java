package com.smartchef.dto;

import com.smartchef.model.Dificultad;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.model.RecetaIngrediente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponseDTO {
    private Long idReceta;
    private String titulo;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Dificultad dificultad;
    private String categoria;
    private String imagen;

    private List<PasoRecetaResponseDTO> pasos;
    private List<PreferenciaAlimentaria> preferencias;
    private List<RecetaIngrediente> ingredientes;

}

