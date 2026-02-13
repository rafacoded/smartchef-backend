package com.smartchef.dto;

public record AuthResponseDTO(
        String token,
        Long idUsuario,
        String email
) {}

