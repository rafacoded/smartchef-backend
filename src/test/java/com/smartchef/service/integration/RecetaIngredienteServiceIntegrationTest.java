package com.smartchef.service.integration;

import com.smartchef.dto.IngredienteUsoDTO;
import com.smartchef.dto.UsuarioRecetaPopularDTO;
import com.smartchef.repository.IRecetaIngredienteRepository;
import com.smartchef.service.GuardadoRecetaService;
import com.smartchef.service.RecetaIngredienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecetaIngredienteServiceIntegrationTest {

    @InjectMocks private RecetaIngredienteService service;

    @Mock private IRecetaIngredienteRepository repo;

    @Mock private GuardadoRecetaService guardadoRecetaService;

    @Test
    @DisplayName("9 Test Integración -> top5Ingredientes()")
    void top5IngredientesIntegrationTest() {

        // Given
        List<Object[]> rows = List.of(
                new Object[]{1L, "Tofu", 5L},
                new Object[]{2L, "Arroz", 3L}
        );

        Mockito.when(repo.topIngredientes(Mockito.any(Pageable.class)))
                .thenReturn(rows);

        // Then
        List<IngredienteUsoDTO> result = service.top5Ingredientes();

        // When
        assertNotNull(result);
        assertEquals(2, result.size());

        Mockito.verify(repo).topIngredientes(Mockito.any(Pageable.class));
    }

    @Test
    @DisplayName("10 Test Integración -> usuariosConRecetaMasPopular()")
    void usuariosConRecetaMasPopularIntegrationTest() {

        // Given
        Mockito.when(guardadoRecetaService.recetaMasGuardada())
                .thenReturn(Optional.of(new Object[]{10L, 7L}));

        List<Object[]> usuarios = List.of(
                new Object[]{1L, "Rafaa", 10L, "Pasta"},
                new Object[]{2L, "Pepe", 10L, "Pasta"}
        );

        Mockito.when(guardadoRecetaService.usuariosQueGuardaronReceta(10L))
                .thenReturn(usuarios);

        // Then
        List<UsuarioRecetaPopularDTO> result = service.usuariosConRecetaMasPopular();

        // When
        assertNotNull(result);
        assertEquals(2, result.size());

        Mockito.verify(guardadoRecetaService).recetaMasGuardada();
        Mockito.verify(guardadoRecetaService).usuariosQueGuardaronReceta(10L);
    }
}
