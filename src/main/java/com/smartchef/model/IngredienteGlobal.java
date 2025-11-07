package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingrediente_global")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredienteGlobal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 50)
    private String categoria;  // Ej: "Verdura", "Lácteo", "Grano"

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_base", length = 20)
    private UnidadMedida unidadBase; // Ej: "g", "ml", "unidad"


}

