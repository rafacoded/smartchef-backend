package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionRecetaDTO {
    private Long idValoracion;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idReceta;
    private String tituloReceta;
    private int puntuacion;
    private String comentario;
    private LocalDateTime fechaValoracion;
}
