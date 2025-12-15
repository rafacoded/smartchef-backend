package com.smartchef.dto;

import com.smartchef.model.Dificultad;
import com.smartchef.model.PreferenciaAlimentaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Data
public class RecetaCrearDTO {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message= "La descripción es obligatoria. ")
    private String descripcion;

    @NotNull(message = "El tiempo de preparación es obligatorio.")
    private Integer tiempoPreparacion;

    @NotNull
    private Dificultad dificultad;

    private String categoria;
    private String imagen;

    @NotEmpty
    private List<PasoRecetaDTO> pasos;

    @NotEmpty
    private List<RecetaIngredienteDTO> ingredientes;

    private List<PreferenciaAlimentaria> preferencias;
}


