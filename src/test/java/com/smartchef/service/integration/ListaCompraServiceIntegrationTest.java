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

    @Mock private ListaItemService listaItemService;

    @Mock private RecetaIngredienteService recetaIngredienteService;

    @Mock private ListaCompraMapper listaCompraMapper;

    @Test
    @DisplayName("6 Test Integración -> generarDesdeReceta()")
    void generarDesdeRecetaIntegrationTest() {

        Long idUsuario = 1L;
        Long idReceta = 10L;

        ListaCompraDTO dto = new ListaCompraDTO();
        dto.setNombreLista("Lista");
        dto.setDescripcion("desc");

        Mockito.when(usuarioService.buscarPorId(Mockito.anyLong())).thenReturn(new Usuario());
        Mockito.when(recetaService.buscarEntityPorId(Mockito.anyLong())).thenReturn(new Receta());
        Mockito.when(recetaIngredienteService.buscarPorReceta(Mockito.anyLong()))
                .thenReturn(List.of(new RecetaIngrediente(), new RecetaIngrediente()));

        Mockito.when(listaCompraRepository.save(Mockito.any(ListaCompra.class)))
                .thenReturn(new ListaCompra());

        Mockito.when(listaCompraMapper.toResponseDTO(Mockito.any(ListaCompra.class), Mockito.anyList()))
                .thenReturn(new ListaCompraResponseDTO());

        service.generarDesdeReceta(idUsuario, idReceta, dto);

        Mockito.verify(usuarioService).buscarPorId(idUsuario);
        Mockito.verify(recetaService).buscarEntityPorId(idReceta);
        Mockito.verify(listaCompraRepository).save(Mockito.any(ListaCompra.class));
        Mockito.verify(recetaIngredienteService).buscarPorReceta(idReceta);
        Mockito.verify(listaItemService).guardarItems(Mockito.anyList());
        Mockito.verify(listaCompraMapper).toResponseDTO(Mockito.any(ListaCompra.class), Mockito.anyList());
    }
}
