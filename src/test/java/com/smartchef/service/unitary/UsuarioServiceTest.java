package com.smartchef.service.unitary;


import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioRegistroDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IUsuarioRepository;

import com.smartchef.service.UsuarioService;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServiceTest {

    @Autowired private UsuarioService usuarioService;

    @Autowired private IUsuarioRepository repository;

    @Test
    @DisplayName("1 Crear usuario -> Caso positivo")
    void crearUsuarioPositivoTest() {
        // Given
        UsuarioRegistroDTO dto = new UsuarioRegistroDTO();
        dto.setNombre("nuevo_user");
        dto.setEmail("nuevo@email.com");
        dto.setPassword("1234");
        dto.setPreferenciasAlimentarias(Collections.singletonList(PreferenciaAlimentaria.VEGETARIANO));

        // Then
        UsuarioResponseDTO response = usuarioService.crearUsuario(dto);

        // When
        assertNotNull(response, "La respuesta no debería ser nula");
        assertNotNull(response.getIdUsuario(), "El usuario debería haberse guardado y tener ID");

        Usuario guardado = repository.findById(response.getIdUsuario()).orElse(null);
        assertNotNull(guardado, "El usuario no se guardó en base de datos");

        assertNotEquals("1234", guardado.getPassword(), "La contraseña no se ha codificado");

        assertEquals(PreferenciaAlimentaria.VEGETARIANO, guardado.getPreferenciasAlimentarias().getFirst(),
                "La preferencia alimentaria no coincide");
    }

    @Test
    @DisplayName("1 Crear usuario -> Caso negativo (password nula)")
    void crearUsuarioNegativoTest() {
        // Given
        UsuarioRegistroDTO dto = new UsuarioRegistroDTO();
        dto.setNombre("user_fail");
        dto.setEmail("fail@email.com");
        dto.setPassword(null);
        dto.setPreferenciasAlimentarias(Collections.singletonList(PreferenciaAlimentaria.VEGANO));

        // Then + When
        assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuario(dto));
    }
}
