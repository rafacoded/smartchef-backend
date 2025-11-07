package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lista_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lista", nullable = false)
    private ListaCompra listaCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private IngredienteGlobal ingrediente;

    @Column(nullable = false)
    private Double cantidad;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private UnidadMedida unidad;

    @Column(nullable = false)
    private boolean comprado = false;
}
