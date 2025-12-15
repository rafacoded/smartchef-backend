package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UsuarioRecetaPopularDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private Long idReceta;
    private String tituloReceta;
    private Long vecesGuardada;

}
