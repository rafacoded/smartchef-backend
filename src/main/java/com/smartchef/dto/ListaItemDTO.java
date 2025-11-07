package com.smartchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaItemDTO {
    private Long idItem;
    private Long idLista;
    private String nombreLista;
    private Long idIngrediente;
    private String nombreIngrediente;
    private Double cantidad;
    private String unidad;
    private boolean comprado;
}
