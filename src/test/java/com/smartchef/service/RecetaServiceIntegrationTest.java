package com.smartchef.service;

import com.smartchef.dto.IngredienteGlobalDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.mapper.RecetaMapper;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.model.Receta;
import com.smartchef.model.RecetaIngrediente;
import com.smartchef.model.UnidadMedida;
import com.smartchef.repository.IIngredienteGlobalRepository;
import com.smartchef.repository.IRecetaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class RecetaServiceIntegrationTest {

    @InjectMocks
    private RecetaService service;

    @Mock
    private IRecetaRepository repository;

    @Mock
    private IIngredienteGlobalRepository ingredienteGlobalRepository;

    @Mock
    private RecetaMapper mapper;

    @Test
    @DisplayName("Test de Integración -> buscarPorId() ")
    public void buscarPorIdIntegrationTest() {

        // Given
        Mockito.when(this.repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Receta()));
        Mockito.when(this.mapper.toResponseDTO(Mockito.any(Receta.class)))
                .thenReturn(new RecetaResponseDTO());
        // When
        this.service.buscarPorId(1L);

        // Then
        Mockito.verify(this.repository).findById(1L);
        Mockito.verify(this.mapper).toResponseDTO(Mockito.any());
    }

//    @Test
//    @DisplayName("Test de integración -> aplicarIngredientes()")
//    public void aplicarIngredientesIntegrationTest() {
//
//        // Given
//        Receta r = new Receta();
//        r.setIngredientes(new ArrayList<>());
//
//        r.getIngredientes().add(RecetaIngrediente.builder().build());
//
//        RecetaIngredienteDTO dto1 = new RecetaIngredienteDTO();
//        dto1.setIdRecetaIngrediente(10L);
//        dto1.setCantidad(2.0);
//        dto1.setUnidad("GRAMO");
//
//        RecetaIngredienteDTO dto2 = new RecetaIngredienteDTO();
//        dto1.setIdRecetaIngrediente(20L);
//        dto1.setCantidad(2.0);
//        dto1.setUnidad("GRAMO");
//
//        IngredienteGlobal ing1 = new IngredienteGlobal();
//        ing1.setIdIngrediente(10L);
//
//        IngredienteGlobal ing2 = new IngredienteGlobal();
//        ing2.setIdIngrediente(20L);
//
//        Mockito.when(this.ingredienteGlobalRepository.findById(10L)).thenReturn(ing1);
//        Mockito.when(this.ingredienteGlobalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
//
//        Receta rec = new Receta();
//        rec.setIdReceta(1L);
//
//
//        // Then
//        this.service.aplicarIngredientes();
//        // When
//        Mockito.verify(this.repository).findById(Mockito.anyLong());
//        Mockito.verify(this.ingredienteGlobalRepository).findById(Mockito.anyLong());
//        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
//    }


}
