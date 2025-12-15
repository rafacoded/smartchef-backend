package com.smartchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColeccionRecetaDTO {

    private Long idColeccion;

    @NotBlank(message = "El nombre de la colección es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    private LocalDateTime fechaCreacion;

    @NotNull(message = "Debe especificarse el usuario propietario")
    private Long idUsuario;

    private Set<Long> idsRecetas;

    private Set<String> titulosRecetas;
}
