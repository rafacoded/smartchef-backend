package com.smartchef.dto;

import com.smartchef.model.PreferenciaAlimentaria;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 100)
    private String password;

    @NotEmpty(message = "Debes incluir al menos una preferencia alimentaria")
    private List<PreferenciaAlimentaria> preferenciasAlimentarias;
}

