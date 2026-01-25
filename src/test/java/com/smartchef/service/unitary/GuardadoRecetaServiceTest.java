package com.smartchef.service.unitary;

import com.smartchef.dto.GuardadoRecetaResponseDTO;
import com.smartchef.exception.OperacionNoPermitidaException;
import com.smartchef.model.*;
import com.smartchef.repository.IGuardadoRecetaRepository;
import com.smartchef.repository.IRecetaRepository;
import com.smartchef.repository.IUsuarioRepository;
import com.smartchef.service.GuardadoRecetaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class GuardadoRecetaServiceTest {

    @Autowired private GuardadoRecetaService service;

    @Autowired private IGuardadoRecetaRepository repo;

    @Autowired private IUsuarioRepository usuarioRepo;

    @Autowired private IRecetaRepository recetaRepo;

    private Long usuarioId;
    private Long recetaId1;
    private Long recetaId2;

    @BeforeEach
    public void cargar() {
        repo.deleteAll();
        recetaRepo.deleteAll();
        usuarioRepo.deleteAll();

        Usuario u1 = new Usuario();
        u1.setNombre("user_fail");
        u1.setEmail("fail@email.com");
        u1.setPassword("1234");
        u1.setPreferenciasAlimentarias(Collections.singletonList(PreferenciaAlimentaria.VEGANO));
        u1 = usuarioRepo.save(u1);
        usuarioId = u1.getIdUsuario();

        Receta receta1 = new Receta();
        receta1.setTitulo("Titulo Receta");
        receta1.setImagen("imagen");
        receta1.setDescripcion("Descripcion Receta");
        receta1.setDificultad(Dificultad.FACIL);
        receta1.setTiempoPreparacion(15);
        receta1.setPreferencias(new ArrayList<>(List.of(PreferenciaAlimentaria.VEGANO)));
        receta1 = recetaRepo.save(receta1);
        recetaId1 = receta1.getIdReceta();

        Receta receta2 = new Receta();
        receta2.setTitulo("Titulo Receta 2");
        receta2.setImagen("imagen2");
        receta2.setDescripcion("Descripcion Receta 2");
        receta2.setDificultad(Dificultad.MEDIA);
        receta2.setTiempoPreparacion(25);
        receta2.setPreferencias(new ArrayList<>(List.of(PreferenciaAlimentaria.VEGETARIANO)));
        receta2 = recetaRepo.save(receta2);
        recetaId2 = receta2.getIdReceta();

        GuardadoReceta guardado1 = GuardadoReceta.builder()
                .receta(receta2)
                .usuario(u1)
                .build();

        repo.save(guardado1);
    }

    @Test
    @DisplayName("5 Marcar receta como guardada -> Caso positivo")
    void guardarRecetaPositivoTest() {
        // Given

        // Then
        GuardadoRecetaResponseDTO dto = service.guardarReceta(usuarioId, recetaId1);
        // When
        assertNotNull(dto, "La respuesta no debería ser nula");
        assertEquals(dto.getUsuario().getIdUsuario(), usuarioId, "El usuario de la respuesta DTO no coincide");
        assertEquals(dto.getReceta().getIdReceta(), recetaId1, "El receta no coincide");
    }


    @Test
    @DisplayName("5 Marcar receta como guardada -> Caso negativo (si ya está guardada)")
    void guardarRecetaNegativoTest() {
        // Given

        // Then

        // When
        assertThrows(OperacionNoPermitidaException.class,
                () -> service.guardarReceta(usuarioId, recetaId2));
    }

}
