package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "valoracion_receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValoracionReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValoracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(nullable = false)
    private int puntuacion; // 1–5 estrellas

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_valoracion")
    private LocalDateTime fechaValoracion = LocalDateTime.now();
}
