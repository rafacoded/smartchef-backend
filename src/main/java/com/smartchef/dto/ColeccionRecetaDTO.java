package com.smartchef.dto;

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
    private Long idUsuario;
    private String nombreUsuario;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Set<Long> idsRecetas; // solo IDs de recetas
    private Set<String> titulosRecetas; // para mostrar nombres en el frontend
}
