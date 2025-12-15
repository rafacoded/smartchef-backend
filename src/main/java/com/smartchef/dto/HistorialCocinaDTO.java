package com.smartchef.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialCocinaDTO {

    @NotNull(message = "Debe indicarse la fecha de realización")
    private LocalDate fechaRealizacion;

    @Size(max = 255, message = "El comentario no puede superar los 255 caracteres")
    private String comentario;

    @Min(value = 1, message = "La valoración mínima es 1")
    @Max(value = 5, message = "La valoración máxima es 5")
    private Integer valoracionPersonal;
}
