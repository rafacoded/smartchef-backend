package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "receta_ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecetaIngrediente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private IngredienteGlobal ingrediente;

    @Column(nullable = false)
    private Double cantidad;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private UnidadMedida unidad; // g, ml, unidad, etc.
}
