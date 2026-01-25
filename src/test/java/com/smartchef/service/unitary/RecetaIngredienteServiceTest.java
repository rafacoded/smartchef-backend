package com.smartchef.service.unitary;

import com.smartchef.dto.IngredienteUsoDTO;
import com.smartchef.dto.UsuarioRecetaPopularDTO;
import com.smartchef.model.*;
import com.smartchef.repository.*;
import com.smartchef.service.RecetaIngredienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecetaIngredienteServiceTest {

    @Autowired
    private RecetaIngredienteService service;

    @Autowired
    private IRecetaIngredienteRepository recetaIngredienteRepository;

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IGuardadoRecetaRepository guardadoRecetaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IIngredienteGlobalRepository ingredienteGlobalRepository;

    // Test ingredientes
    private Long idIngredienteA;
    private Long idIngredienteB;

    // Test usuarios
    private Long idRecetaPopular;


    @BeforeAll
    void cargarDatos() {

        // Usuarios
        Usuario u1 = new Usuario();
        u1.setNombre("Ana");
        u1.setEmail("ana@email.com");
        u1.setPassword("pass");
        u1.setPreferenciasAlimentarias(List.of());

        Usuario u2 = new Usuario();
        u2.setNombre("Luis");
        u2.setEmail("luis@email.com");
        u2.setPassword("pass");
        u2.setPreferenciasAlimentarias(List.of());

        u1 = usuarioRepository.save(u1);
        u2 = usuarioRepository.save(u2);

        // Ingredientes
        IngredienteGlobal ingA = IngredienteGlobal.builder()
                .nombre("Tomate")
                .categoria("Verdura")
                .unidadBase(UnidadMedida.GRAMO)
                .build();

        IngredienteGlobal ingB = IngredienteGlobal.builder()
                .nombre("Aceite")
                .categoria("Grasa")
                .unidadBase(UnidadMedida.MILILITRO)
                .build();

        ingA = ingredienteGlobalRepository.save(ingA);
        ingB = ingredienteGlobalRepository.save(ingB);

        idIngredienteA = ingA.getIdIngrediente();
        idIngredienteB = ingB.getIdIngrediente();

        // Recetas
        Receta r1 = Receta.builder()
                .titulo("Receta 1")
                .descripcion("Desc 1")
                .tiempoPreparacion(10)
                .dificultad(Dificultad.FACIL)
                .categoria("Cena")
                .imagen("img1")
                .build();

        Receta r2 = Receta.builder()
                .titulo("Receta 2")
                .descripcion("Desc 2")
                .tiempoPreparacion(15)
                .dificultad(Dificultad.FACIL)
                .categoria("Cena")
                .imagen("img2")
                .build();

        r1 = recetaRepository.save(r1);
        r2 = recetaRepository.save(r2);

        idRecetaPopular = r1.getIdReceta();

        // Guardados
        guardadoRecetaRepository.save(GuardadoReceta.builder().usuario(u1).receta(r1).build());
        guardadoRecetaRepository.save(GuardadoReceta.builder().usuario(u2).receta(r1).build());
        guardadoRecetaRepository.save(GuardadoReceta.builder().usuario(u1).receta(r2).build());

        RecetaIngrediente ri1 = new RecetaIngrediente();
        ri1.setCantidad(100.0);
        ri1.setUnidad(UnidadMedida.GRAMO);
        ri1.setReceta(r1);
        ri1.setIngrediente(ingA);

        RecetaIngrediente ri2 = new RecetaIngrediente();
        ri2.setCantidad(200.0);
        ri2.setUnidad(UnidadMedida.GRAMO);
        ri2.setReceta(r2);
        ri2.setIngrediente(ingA); // A aparece en 2 recetas

        RecetaIngrediente ri3 = new RecetaIngrediente();
        ri3.setCantidad(10.0);
        ri3.setUnidad(UnidadMedida.MILILITRO);
        ri3.setReceta(r1);
        ri3.setIngrediente(ingB); // B aparece en 1 receta

        recetaIngredienteRepository.save(ri1);
        recetaIngredienteRepository.save(ri2);
        recetaIngredienteRepository.save(ri3);

    }

    @Test
    @DisplayName("10 Estadísticas -> usuarioPopular Caso Positivo")
    void usuariosConRecetaMasPopularPositivoTest() {

        // Given
        List<UsuarioRecetaPopularDTO> result = service.usuariosConRecetaMasPopular();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size(), "Deben aparecer 2 usuarios que guardaron la receta más popular");

        for (UsuarioRecetaPopularDTO dto : result) {
            assertEquals(idRecetaPopular, dto.getIdReceta());
            assertEquals("Receta 1", dto.getTituloReceta());
            assertEquals(2L, dto.getVecesGuardada());
        }
    }

    @Test
    @DisplayName("10 Estadísticas -> usuarioPopular Caso Negativo (sin guardados devuelve vacío)")
    void usuariosConRecetaMasPopularNegativoTest() {

        // Given: dejar sin guardados
        guardadoRecetaRepository.deleteAll();

        // Then
        List<UsuarioRecetaPopularDTO> result = service.usuariosConRecetaMasPopular();

        // When
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("9 Estadísticas -> top5Ingredientes Caso Positivo")
    void top5IngredientesPositivoTest() {

        // Given: 2 ingredientes que constan en 2 diferentes recetas
        List<IngredienteUsoDTO> result = service.top5Ingredientes();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Debería devolver estadísticas");

        assertTrue(result.size() <= 5);
        assertEquals(2, result.size());

        IngredienteUsoDTO top1 = result.getFirst();
        assertEquals(idIngredienteA, top1.getIdIngrediente());
        assertEquals("Tomate", top1.getNombre());
        assertEquals(2L, top1.getNumeroRecetas());

        IngredienteUsoDTO top2 = result.get(1);
        assertEquals(idIngredienteB, top2.getIdIngrediente());
        assertEquals("Aceite", top2.getNombre());
        assertEquals(1L, top2.getNumeroRecetas());}

    @Test
    @DisplayName("9 Estadísticas -> top5Ingredientes Caso Negativo (sin datos devuelve vacío)")
    void top5IngredientesNegativoTest() {

        // Given (ausencia de datos)
        recetaIngredienteRepository.deleteAll();

        // Then
        List<IngredienteUsoDTO> result = service.top5Ingredientes();

        // When
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}

