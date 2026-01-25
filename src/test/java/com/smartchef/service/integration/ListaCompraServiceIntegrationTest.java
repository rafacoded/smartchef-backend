package com.smartchef.service.integration;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.dto.ListaCompraResponseDTO;
import com.smartchef.model.*;
import com.smartchef.repository.IListaCompraRepository;
import com.smartchef.service.*;
import com.smartchef.mapper.ListaCompraMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListaCompraServiceIntegrationTest {

    @InjectMocks private ListaCompraService service;

    @Mock private IListaCompraRepository listaCompraRepository;

    @Mock private UsuarioService usuarioService;

    @Mock private RecetaService recetaService;

    @Mock private RecetaIngredienteService recetaIngredienteService;

    @Mock private ListaCompraMapper listaCompraMapper;

    @Test
    @DisplayName("6 Test Integración -> generarDesdeReceta()")
    void generarDesdeRecetaIntegrationTest() {

        // Given
        Long idUsuario = 1L;
        Long idReceta = 10L;

        ListaCompraDTO dto = new ListaCompraDTO();
        dto.setNombreLista("Lista semana");
        dto.setDescripcion("Compra para la receta");

        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario);

        Receta r = new Receta();
        r.setIdReceta(idReceta);

        IngredienteGlobal ing1 = new IngredienteGlobal();
        ing1.setIdIngrediente(100L);
        ing1.setNombre("Leche");

        IngredienteGlobal ing2 = new IngredienteGlobal();
        ing2.setIdIngrediente(200L);
        ing2.setNombre("Harina");

        RecetaIngrediente ri1 = RecetaIngrediente.builder()
                .ingrediente(ing1).cantidad(200.0).unidad(UnidadMedida.MILILITRO)
                .build();

        RecetaIngrediente ri2 = RecetaIngrediente.builder()
                .ingrediente(ing2).cantidad(100.0).unidad(UnidadMedida.GRAMO)
                .build();

        Mockito.when(usuarioService.buscarPorId(idUsuario)).thenReturn(u);
        Mockito.when(recetaService.buscarEntityPorId(idReceta)).thenReturn(r);
        Mockito.when(recetaIngredienteService.buscarPorReceta(idReceta)).thenReturn(List.of(ri1, ri2));

        ListaCompra listaGuardada = ListaCompra.builder()
                .idLista(999L)
                .nombreLista(dto.getNombreLista())
                .descripcion(dto.getDescripcion())
                .usuario(u)
                .origenReceta(r)
                .activa(true)
                .build();

        Mockito.when(listaCompraRepository.save(Mockito.any(ListaCompra.class))).thenReturn(listaGuardada);

        ListaCompraResponseDTO responseMock = new ListaCompraResponseDTO();
        Mockito.when(listaCompraMapper.toResponseDTO(Mockito.any(ListaCompra.class), Mockito.anyList()))
                .thenReturn(responseMock);

        // Then
        service.generarDesdeReceta(idUsuario, idReceta, dto);

        // When
        Mockito.verify(usuarioService).buscarPorId(idUsuario);
        Mockito.verify(recetaService).buscarEntityPorId(idReceta);
        Mockito.verify(listaCompraRepository).save(Mockito.any(ListaCompra.class));
        Mockito.verify(recetaIngredienteService).buscarPorReceta(idReceta);

    }
}
