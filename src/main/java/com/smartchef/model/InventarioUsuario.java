package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    //  Cantidad y unidad actual del ingrediente
    @Column(nullable = false)
    private Double cantidad;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UnidadMedida unidad;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    // Usuario propietario del inventario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Ingrediente global (referencia al catálogo)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private IngredienteGlobal ingrediente;
}

