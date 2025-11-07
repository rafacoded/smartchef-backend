package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String pasos;

    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion; // minutos

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Dificultad dificultad;

    @Column(length = 100)
    private String categoria; // "Desayuno", "Cena" maybe incluso -> "Plato principal" "Entrante" "Postre"

    @Column
    private String imagen;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // 🔗 Relación con usuario (autor)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario autor;
}
