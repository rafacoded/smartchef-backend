package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paso_receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasoReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaso;

    private Integer orden;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta")
    private Receta receta;
}