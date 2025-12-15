package com.smartchef.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaItemDTO {

    private Long idItem;

    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.1", message = "La cantidad debe ser mayor que 0")
    private Double cantidad;

    @NotBlank(message = "Debe indicar la unidad de medida")
    private String unidad;

    private Boolean comprado = false;

    @NotNull(message = "Debe indicar el ingrediente")
    private Long idIngrediente;

    @NotNull(message = "Debe indicar la lista de la compra a la que pertenece")
    private Long idLista;
}

