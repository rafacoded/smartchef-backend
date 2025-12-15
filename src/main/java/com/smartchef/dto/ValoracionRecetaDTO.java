package com.smartchef.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionRecetaDTO {

    private Long idValoracion;

    @Min(value = 1, message = "La valoración mínima es 1 estrella")
    @Max(value = 5, message = "La valoración máxima es 5 estrellas")
    private Integer puntuacion;

    @Size(max = 255, message = "El comentario no puede superar los 255 caracteres")
    private String comentario;

    private LocalDateTime fechaValoracion;

    @NotNull(message = "Debe especificarse el usuario que valora")
    private Long idUsuario;

    @NotNull(message = "Debe especificarse la receta valorada")
    private Long idReceta;
}
