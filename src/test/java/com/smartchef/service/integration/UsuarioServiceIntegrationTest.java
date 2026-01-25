package com.smartchef.service.integration;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.mapper.UsuarioMapper;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IUsuarioRepository;
import com.smartchef.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceIntegrationTest {

    @InjectMocks private UsuarioService service;

    @Mock private IUsuarioRepository usuarioRepository;

    @Mock private UsuarioMapper usuarioMapper;

    @Mock private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("1 Test de Integración -> crearUsuario()")
    void crearUsuarioIntegrationTest() {

        // Given
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("nuevo_user");
        dto.setEmail("nuevo@email.com");
        dto.setPassword("1234");
        dto.setPreferenciasAlimentarias(List.of(PreferenciaAlimentaria.VEGETARIANO));

        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNombre("nuevo_user");
        usuarioEntity.setEmail("nuevo@email.com");
        usuarioEntity.setPassword("1234");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setIdUsuario(1L);
        usuarioGuardado.setNombre("nuevo_user");
        usuarioGuardado.setEmail("nuevo@email.com");
        usuarioGuardado.setPassword("HASHED");

        UsuarioResponseDTO responseMock = new UsuarioResponseDTO();
        responseMock.setIdUsuario(1L);
        responseMock.setNombre("nuevo_user");
        responseMock.setEmail("nuevo@email.com");
        responseMock.setPreferenciasAlimentarias(List.of(PreferenciaAlimentaria.VEGETARIANO));

        Mockito.when(usuarioMapper.toEntity(Mockito.any(UsuarioDTO.class))).thenReturn(usuarioEntity);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("HASHED");
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioGuardado);
        Mockito.when(usuarioMapper.toResponseDTO(Mockito.any(Usuario.class))).thenReturn(responseMock);

        // Then
        UsuarioResponseDTO result = service.crearUsuario(dto);

        // When
        assertNotNull(result);
        assertEquals(1L, result.getIdUsuario());

        Mockito.verify(usuarioMapper).toEntity(Mockito.any(UsuarioDTO.class));
        Mockito.verify(passwordEncoder).encode("1234");
        Mockito.verify(usuarioRepository).save(Mockito.any(Usuario.class));
        Mockito.verify(usuarioMapper).toResponseDTO(Mockito.any(Usuario.class));
    }
}
