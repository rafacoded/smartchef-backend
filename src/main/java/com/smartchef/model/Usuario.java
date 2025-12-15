package com.smartchef.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data                       // incluye getter, setter, equals, hashCode, toString
@NoArgsConstructor           // constructor vacío
@AllArgsConstructor          // constructor con todos los campos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "preferencias_alimentarias")
    private List<PreferenciaAlimentaria> preferenciasAlimentarias;

    @Column(name = "foto_perfil")
    private String fotoPerfil;
}