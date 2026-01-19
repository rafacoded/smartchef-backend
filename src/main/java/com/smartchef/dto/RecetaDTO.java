package com.smartchef.dto;

import com.smartchef.model.PreferenciaAlimentaria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDTO {
    private Long idReceta;

    @NotBlank(message = "El título de la receta es obligatorio")
    @Size(max = 150, message = "El título no puede superar los 150 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El tiempo de preparación es obligatorio")
    @Min(value = 1, message = "El tiempo de preparación debe ser al menos 1 minuto")
    private Integer tiempoPreparacion;

    @Size(max = 100, message = "La categoría no puede superar los 100 caracteres")
    private String categoria;

    private String dificultad;
    private String imagen;

    private LocalDateTime fechaCreacion;
    private List<PasoRecetaDTO> pasos;
    private List<PreferenciaAlimentaria> preferencias;


}
