package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "guardado_receta", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_usuario", "id_receta"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardadoReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGuardado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(name = "fecha_guardado")
    private LocalDateTime fechaGuardado = LocalDateTime.now();
}
