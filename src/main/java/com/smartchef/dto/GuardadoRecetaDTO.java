package com.smartchef.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardadoRecetaDTO {

    private Long idGuardado;

    @NotNull(message = "Debe especificarse el usuario")
    private Long idUsuario;

    @NotNull(message = "Debe especificarse la receta guardada")
    private Long idReceta;

    private Long idColeccion;
}
