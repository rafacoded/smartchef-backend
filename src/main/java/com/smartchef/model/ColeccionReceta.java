package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coleccion_receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColeccionReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColeccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // 🔗 Relación N:M con recetas a través de tabla intermedia
    @ManyToMany
    @JoinTable(
            name = "coleccion_receta_detalle",
            joinColumns = @JoinColumn(name = "id_coleccion"),
            inverseJoinColumns = @JoinColumn(name = "id_receta")
    )
    private Set<Receta> recetas = new HashSet<>();
}
