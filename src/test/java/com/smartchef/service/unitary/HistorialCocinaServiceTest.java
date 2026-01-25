package com.smartchef.service.unitary;

import com.smartchef.dto.HistorialCocinaResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.exception.ValorNoValidoException;

import com.smartchef.model.Dificultad;
import com.smartchef.model.HistorialCocina;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;

import com.smartchef.repository.IHistorialCocinaRepository;
import com.smartchef.repository.IRecetaRepository;
import com.smartchef.repository.IUsuarioRepository;

import com.smartchef.service.HistorialCocinaService;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.exception.OperacionNoPermitidaException;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HistorialCocinaServiceTest {

    @Autowired private HistorialCocinaService service;

    @Autowired private IHistorialCocinaRepository repository;

    @Autowired private IUsuarioRepository usuarioRepository;

    @Autowired private IRecetaRepository recetaRepository;

    private Long idUsuario;
    private Long idReceta;
    private LocalDate fechaYaRegistrada;
    private LocalDate fechaLibre;


    // Con BeforeAll da error por el entityManager (transactional ????)
    @BeforeEach
    void cargarDatos() {

        repository.deleteAll();
        recetaRepository.deleteAll();
        usuarioRepository.deleteAll();

        Usuario u = new Usuario();
        u.setNombre("Rafaa");
        u.setEmail("rafaa@email.com");
        u.setPassword("pass");
        u.setPreferenciasAlimentarias(List.of());
        u = usuarioRepository.save(u);

        Receta r = new Receta();
        r.setTitulo("Pasta");
        r.setTiempoPreparacion(10);
        r.setDificultad(Dificultad.FACIL);
        r.setCategoria("Cena");
        r = recetaRepository.save(r);

        idUsuario = u.getIdUsuario();
        idReceta = r.getIdReceta();

        LocalDate base = LocalDate.of(2026, 1, 14);
        LocalDate lunes = base.with(DayOfWeek.MONDAY);

        fechaYaRegistrada = lunes.plusDays(1);
        fechaLibre = lunes.plusDays(3);

        HistorialCocina hDentro = HistorialCocina.builder()
                .usuario(u)
                .receta(r)
                .fechaRealizacion(fechaYaRegistrada)
                .comentario("rico")
                .valoracionPersonal(5)
                .build();

        HistorialCocina hFuera = HistorialCocina.builder()
                .usuario(u)
                .receta(r)
                .fechaRealizacion(lunes.minusDays(2))
                .build();

        repository.save(hDentro);
        repository.save(hFuera);
    }

    @Test
    @DisplayName("7 Registrar receta cocinada -> Caso positivo")
    void registrarPositivoTest() {
        // Given
        HistorialCocinaDTO dto = new HistorialCocinaDTO();
        dto.setFechaRealizacion(fechaLibre);
        dto.setComentario("salió increíble");
        dto.setValoracionPersonal(4);

        // Then
        HistorialCocinaResponseDTO response = service.registrar(idUsuario, idReceta, dto);

        // When
        assertNotNull(response, "La respuesta no debería ser nula");
        assertNotNull(response.getIdHistorial(), "Debería devolver idHistorial");
        assertEquals(fechaLibre, response.getFechaRealizacion());
        assertEquals("salió increíble", response.getComentario());
        assertEquals(4, response.getValoracionPersonal());

        assertNotNull(response.getUsuario());
        assertEquals(idUsuario, response.getUsuario().getIdUsuario());

        assertNotNull(response.getReceta());
        assertEquals(idReceta, response.getReceta().getIdReceta());

        assertTrue(repository.existsByUsuarioAndRecetaAndFechaRealizacion(
                usuarioRepository.findById(idUsuario).orElseThrow(),
                recetaRepository.findById(idReceta).orElseThrow(),
                fechaLibre
        ));
    }

    @Test
    @DisplayName("7 Registrar receta cocinada -> Caso negativo (misma receta el mismo día)")
    void registrarNegativoTest() {
        // Given
        HistorialCocinaDTO dto = new HistorialCocinaDTO();
        dto.setFechaRealizacion(fechaYaRegistrada);
        dto.setComentario("Otra vez");
        dto.setValoracionPersonal(3);

        assertThrows(OperacionNoPermitidaException.class,
                () -> service.registrar(idUsuario, idReceta, dto));
    }


    @Test
    @DisplayName("8 Consultar historial semanal -> Caso Positivo")
    void obtenerHistorialSemanalPositivoTest() {

        // Given
        LocalDate base = LocalDate.of(2026, 1, 14);

        // Then
        List<HistorialCocinaResponseDTO> result = service.obtenerHistorialSemanal(idUsuario, base);

        // When
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("8 Historial semanal -> Caso negativo (idUsuario inválido / usuario no existente con id)")
    void obtenerHistorialSemanalNegativoTest() {
        assertThrows(ValorNoValidoException.class,
                () -> service.obtenerHistorialSemanal(0L, LocalDate.of(2026,1,14)));

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.obtenerHistorialSemanal(59L, LocalDate.of(2026,1,14)));
    }
}
