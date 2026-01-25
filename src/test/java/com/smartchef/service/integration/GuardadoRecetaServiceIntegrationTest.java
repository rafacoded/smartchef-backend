package com.smartchef.service.integration;

import com.smartchef.exception.OperacionNoPermitidaException;
import com.smartchef.model.GuardadoReceta;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IGuardadoRecetaRepository;
import com.smartchef.service.GuardadoRecetaService;
import com.smartchef.service.RecetaService;
import com.smartchef.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class GuardadoRecetaServiceIntegrationTest {

    @InjectMocks
    private GuardadoRecetaService service;

    @Mock
    private IGuardadoRecetaRepository repo;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private RecetaService recetaService;

    @Test
    @DisplayName("5 Test Integración -> guardarReceta() ( Negativo: lanza excepción )")
    void guardarRecetaIntegrationTest() {

        // Given
        Long idUsuario = 1L;
        Long idReceta = 2L;

        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario);

        Receta r = new Receta();
        r.setIdReceta(idReceta);

        Mockito.when(usuarioService.buscarPorId(idUsuario)).thenReturn(u);
        Mockito.when(recetaService.buscarEntityPorId(idReceta)).thenReturn(r);

        Mockito.when(repo.existsByUsuarioIdUsuarioAndRecetaIdReceta(idUsuario, idReceta))
                .thenReturn(true);

        // Then + When
        assertThrows(OperacionNoPermitidaException.class,
                () -> service.guardarReceta(idUsuario, idReceta));

        Mockito.verify(usuarioService).buscarPorId(idUsuario);
        Mockito.verify(recetaService).buscarEntityPorId(idReceta);
        Mockito.verify(repo).existsByUsuarioIdUsuarioAndRecetaIdReceta(idUsuario, idReceta);
        Mockito.verify(repo, Mockito.never()).save(Mockito.any(GuardadoReceta.class));
    }
}
