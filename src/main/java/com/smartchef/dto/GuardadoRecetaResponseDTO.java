package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardadoRecetaResponseDTO {

    private Long idGuardado;
    private LocalDate fechaGuardado;

    private UsuarioResumenDTO usuario;
    private RecetaResumenDTO receta;
}
