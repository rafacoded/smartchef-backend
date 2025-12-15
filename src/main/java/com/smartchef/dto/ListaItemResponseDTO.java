package com.smartchef.dto;

import com.smartchef.model.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaItemResponseDTO {

    private Long idItem;

    private String nombreIngrediente;

    private Double cantidad;

    private UnidadMedida unidad;

    private Boolean comprado;
}

