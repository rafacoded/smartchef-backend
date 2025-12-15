package com.smartchef.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsuarioResumenDTO {
    private Long idUsuario;
    private String nombre;
    private String email;
}
