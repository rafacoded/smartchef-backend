package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaCompraDTO {
    private Long idLista;
    private String nombreLista;
    private String descripcion;
    private Boolean activa;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    private Long idUsuario;
    private String nombreUsuario;  // opcional para mostrar quién la creó

    private Long idRecetaOrigen;   // si deriva de una receta
    private String tituloRecetaOrigen;
}
