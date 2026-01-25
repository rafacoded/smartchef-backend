package com.smartchef.service.integration;

import com.smartchef.dto.PasoRecetaDTO;
import com.smartchef.dto.RecetaCrearDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.model.Receta;
import com.smartchef.model.UnidadMedida;
import com.smartchef.model.Dificultad;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.repository.IRecetaRepository;
import com.smartchef.service.IngredienteGlobalService;
import com.smartchef.service.RecetaService;
import com.smartchef.mapper.RecetaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecetaServiceIntegrationTest {

    @InjectMocks
    private RecetaService service;

    @Mock
    private IRecetaRepository recetaRepository;

    @Mock
    private IngredienteGlobalService ingredienteService;

    @Mock
    private RecetaMapper recetaMapper;

    @Test
    @DisplayName("2 Test de Integración -> crearReceta()")
    void crearRecetaIntegrationTest() {

        // Given
        RecetaCrearDTO dto = new RecetaCrearDTO();
        dto.setTitulo("Crepe de prueba");
        dto.setDescripcion("Una receta para test");
        dto.setTiempoPreparacion(12);
        dto.setDificultad(Dificultad.FACIL);
        dto.setCategoria("Desayuno");
        dto.setImagen("img");
        dto.setPreferencias(List.of(PreferenciaAlimentaria.VEGETARIANO));

        PasoRecetaDTO p1 = new PasoRecetaDTO();
        p1.setOrden(1);
        p1.setDescripcion("Mezclar ingredientes");
        PasoRecetaDTO p2 = new PasoRecetaDTO();
        p2.setOrden(2);
        p2.setDescripcion("Cocinar en la sartén");
        dto.setPasos(List.of(p1, p2));

        RecetaIngredienteDTO i1 = new RecetaIngredienteDTO();
        i1.setIdIngrediente(1L);
        i1.setCantidad(150.0);
        i1.setUnidad(UnidadMedida.MILILITRO.name());

        RecetaIngredienteDTO i2 = new RecetaIngredienteDTO();
        i2.setIdIngrediente(2L);
        i2.setCantidad(200.0);
        i2.setUnidad(UnidadMedida.GRAMO.name());

        dto.setIngredientes(List.of(i1, i2));

        IngredienteGlobal ing1 = new IngredienteGlobal();
        ing1.setIdIngrediente(1L);

        IngredienteGlobal ing2 = new IngredienteGlobal();
        ing2.setIdIngrediente(2L);

        Mockito.when(ingredienteService.buscarEntityPorId(1L)).thenReturn(ing1);
        Mockito.when(ingredienteService.buscarEntityPorId(2L)).thenReturn(ing2);

        Receta recetaConId = new Receta();
        recetaConId.setIdReceta(99L);

        Mockito.when(recetaRepository.save(Mockito.any(Receta.class))).thenReturn(recetaConId);

        RecetaResponseDTO dtoResponse = new RecetaResponseDTO();
        dtoResponse.setIdReceta(99L);
        Mockito.when(recetaMapper.toResponseDTO(Mockito.any(Receta.class))).thenReturn(dtoResponse);

        // Then
        RecetaResponseDTO result = service.crearReceta(dto);

        // When
        assertNotNull(result);
        assertEquals(99L, result.getIdReceta());

        // When 2.o
        Mockito.verify(ingredienteService).buscarEntityPorId(1L);
        Mockito.verify(ingredienteService).buscarEntityPorId(2L);

        Mockito.verify(recetaRepository, Mockito.times(2)).save(Mockito.any(Receta.class));
        Mockito.verify(recetaMapper).toResponseDTO(Mockito.any(Receta.class));
    }

    @Test
    @DisplayName("3 Test de Integración -> filtrarRecetas()")
    void filtrarRecetasIntegrationTest() {

        // Given
        String categoria = "   ";
        List<PreferenciaAlimentaria> preferencias = Collections.emptyList();

        Mockito.when(recetaRepository.findAll()).thenReturn(List.of(new Receta(), new Receta()));
        Mockito.when(recetaMapper.toResponseList(Mockito.anyList()))
                .thenReturn(List.of(new RecetaResponseDTO(), new RecetaResponseDTO()));

        // Then
        service.filtrarRecetas(categoria, preferencias);

        // WHEN
        Mockito.verify(recetaRepository).findAll();
        Mockito.verify(recetaRepository, Mockito.never()).filtrar(Mockito.any(), Mockito.any());
        Mockito.verify(recetaMapper).toResponseList(Mockito.anyList());
    }

    @Test
    @DisplayName("4 Test de Integración -> buscarPorId() (Negativo - no encontrada lanza excepción)")
    void buscarPorIdIntegrationTest() {

        Mockito.when(recetaRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.buscarPorId(1L));

        Mockito.verify(recetaRepository).findById(Mockito.anyLong());
        Mockito.verify(recetaMapper, Mockito.never()).toResponseDTO(Mockito.any());
    }
}

