package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaCompraResponseDTO {

    private Long idLista;

    private String nombreLista;

    private String descripcion;

    private LocalDateTime fechaCreacion;

    private Boolean activa;

    private UsuarioResumenDTO usuario;

    private List<ListaItemResponseDTO> items;
}

