package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial_cocina")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(columnDefinition = "TEXT")
    private String comentario; // opcional

    @Column
    private Integer valoracionPersonal; // 1–5 opcional
}
