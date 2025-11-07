package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardadoRecetaDTO {
    private Long idGuardado;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idReceta;
    private String tituloReceta;
    private LocalDateTime fechaGuardado;
}
