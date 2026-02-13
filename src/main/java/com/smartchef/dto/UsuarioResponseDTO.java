package com.smartchef.dto;

import com.smartchef.model.PreferenciaAlimentaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String fechaRegistro;
    private String fotoPerfil;
    private List<PreferenciaAlimentaria> preferenciasAlimentarias;
}
