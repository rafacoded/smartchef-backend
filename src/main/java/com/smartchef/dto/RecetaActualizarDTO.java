package com.smartchef.dto;

import com.smartchef.model.Dificultad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecetaActualizarDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    private Integer tiempoPreparacion;

    @NotNull
    private Dificultad dificultad;

    private String imagen;

    @NotEmpty
    private List<PasoRecetaDTO> pasos;

    @NotEmpty
    private List<RecetaIngredienteDTO> ingredientes;
}
