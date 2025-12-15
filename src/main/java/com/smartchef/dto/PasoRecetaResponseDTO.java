package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasoRecetaResponseDTO {
    private Long idPaso;
    private Integer orden;
    private String descripcion;
}

