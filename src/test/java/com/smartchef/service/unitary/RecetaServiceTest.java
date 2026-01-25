package com.smartchef.service.unitary;

import com.smartchef.dto.PasoRecetaDTO;
import com.smartchef.dto.RecetaCrearDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.model.*;
import com.smartchef.repository.IIngredienteGlobalRepository;
import com.smartchef.repository.IRecetaRepository;
import com.smartchef.service.RecetaService;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecetaServiceTest {

    @Autowired private RecetaService recetaService;

    @Autowired private IRecetaRepository repository;

    @Autowired private IIngredienteGlobalRepository ingredienteGlobalRepository;

    private Long idR1;

    private Long idIng1;
    private Long idIng2;

    @BeforeEach
    void cargarDatos() {
        repository.deleteAll();
        ingredienteGlobalRepository.deleteAll();

        // Ingredientes globales
        IngredienteGlobal ing1 = new IngredienteGlobal();
        ing1.setNombre("Tofu");
        ing1.setCategoria("proteico");
        ing1.setUnidadBase(UnidadMedida.GRAMO);

        IngredienteGlobal ing2 = new IngredienteGlobal();
        ing2.setNombre("Arroz");
        ing2.setCategoria("grano");
        ing2.setUnidadBase(UnidadMedida.GRAMO);

        ing1 = ingredienteGlobalRepository.save(ing1);
        ing2 = ingredienteGlobalRepository.save(ing2);

        idIng1 = ing1.getIdIngrediente();
        idIng2 = ing2.getIdIngrediente();

        // Receta 1
        Receta r1 = new Receta();
        r1.setTitulo("Cena vegana 1");
        r1.setCategoria("Cena");
        r1.setDificultad(Dificultad.FACIL);
        r1.setTiempoPreparacion(10);
        r1.setPreferencias(new ArrayList<>(List.of(PreferenciaAlimentaria.VEGANO)));

        // Pasos
        PasoReceta paso1 = PasoReceta.builder()
                .orden(1)
                .descripcion("Cocer arroz")
                .receta(r1)
                .build();

        PasoReceta paso2 = PasoReceta.builder()
                .orden(2)
                .descripcion("Saltear tofu")
                .receta(r1)
                .build();

        r1.getPasos().add(paso1);
        r1.getPasos().add(paso2);

        // Ingredientes
        RecetaIngrediente ri1 = RecetaIngrediente.builder()
                .receta(r1)
                .ingrediente(ing1)
                .cantidad(150.0)
                .unidad(UnidadMedida.GRAMO)
                .build();

        RecetaIngrediente ri2 = RecetaIngrediente.builder()
                .receta(r1)
                .ingrediente(ing2)
                .cantidad(200.0)
                .unidad(UnidadMedida.GRAMO)
                .build();

        r1.getIngredientes().add(ri1);
        r1.getIngredientes().add(ri2);

        // Receta 2
        Receta r2 = new Receta();
        r2.setTitulo("Cena vegetariana 1");
        r2.setCategoria("Cena");
        r2.setDificultad(Dificultad.MEDIA);
        r2.setTiempoPreparacion(20);
        r2.setPreferencias(new ArrayList<>(List.of(PreferenciaAlimentaria.VEGETARIANO)));

        // Receta 3
        Receta r3 = new Receta();
        r3.setTitulo("Desayuno simple");
        r3.setCategoria("Desayuno");
        r3.setDificultad(Dificultad.FACIL);
        r3.setTiempoPreparacion(5);
        r3.setPreferencias(new ArrayList<>());

        r1 = repository.save(r1);
        repository.save(r2);
        repository.save(r3);

        idR1 = r1.getIdReceta();
    }


    @Test
    @DisplayName("2 Crear receta -> Caso positivo: guarda receta con pasos e ingredientes")
    void crearRecetaPositivoTest() {
        // Given
        RecetaCrearDTO dto = new RecetaCrearDTO();
        dto.setTitulo("Crepe de prueba");
        dto.setDescripcion("Una receta para test");
        dto.setTiempoPreparacion(12);
        dto.setDificultad(Dificultad.FACIL);
        dto.setCategoria("Desayuno");
        dto.setImagen("img");
        dto.setPreferencias(new ArrayList<>(List.of(PreferenciaAlimentaria.VEGETARIANO)));

        // Pasos
        PasoRecetaDTO p1 = new PasoRecetaDTO();
        p1.setOrden(1);
        p1.setDescripcion("Mezclar ingredientes");

        PasoRecetaDTO p2 = new PasoRecetaDTO();
        p2.setOrden(2);
        p2.setDescripcion("Cocinar en la sartén");

        dto.setPasos(List.of(p1, p2));

        // Ingredientes (globales)

        IngredienteGlobal ing1 = new IngredienteGlobal();
        ing1.setNombre("Leche");
        ing1.setCategoria("lacteo");
        ing1.setUnidadBase(UnidadMedida.MILILITRO);

        IngredienteGlobal ing2 = new IngredienteGlobal();
        ing2.setNombre("Harina");
        ing2.setCategoria("grano");
        ing2.setUnidadBase(UnidadMedida.GRAMO);

        ing1 = ingredienteGlobalRepository.save(ing1);
        ing2 = ingredienteGlobalRepository.save(ing2);

        idIng1 = ing1.getIdIngrediente();
        idIng2 = ing2.getIdIngrediente();

        // Ingredientes (recetas)
        RecetaIngredienteDTO i1 = new RecetaIngredienteDTO();
        i1.setIdIngrediente(idIng1);
        i1.setCantidad(150.0);
        i1.setUnidad(String.valueOf(UnidadMedida.MILILITRO));

        RecetaIngredienteDTO i2 = new RecetaIngredienteDTO();
        i2.setIdIngrediente(idIng2);
        i2.setCantidad(200.0);
        i2.setUnidad(String.valueOf(UnidadMedida.GRAMO));

        dto.setIngredientes(List.of(i1, i2));

        // When
        RecetaResponseDTO response = recetaService.crearReceta(dto);

        // Then
        assertNotNull(response);
        assertNotNull(response.getIdReceta(), "La receta creada debería tener ID");

        // Then
        Receta guardada = repository.findById(response.getIdReceta()).orElseThrow();

        assertEquals("Crepe de prueba", guardada.getTitulo());
        assertEquals(2, guardada.getPasos().size(), "Debería guardar 2 pasos");
        assertEquals(2, guardada.getIngredientes().size(), "Debería guardar 2 ingredientes");

    }

    @Test
    @DisplayName("2 Crear receta -> Caso negativo: ingrediente no existe y lanza excepción")
    void crearRecetaNegativoTest() {
        // Given
        RecetaCrearDTO dto = new RecetaCrearDTO();
        dto.setTitulo("Receta mala");
        dto.setDescripcion("mala receta ns");
        dto.setTiempoPreparacion(5);
        dto.setDificultad(Dificultad.FACIL);
        dto.setCategoria("Cena");
        dto.setPreferencias(new ArrayList<>());

        PasoRecetaDTO p1 = new PasoRecetaDTO();
        p1.setOrden(1);
        p1.setDescripcion("Paso 1");
        dto.setPasos(List.of(p1));

        // ingrediente NO existente
        RecetaIngredienteDTO ingFail = new RecetaIngredienteDTO();
        ingFail.setIdIngrediente(999999L);
        ingFail.setCantidad(1.0);
        ingFail.setUnidad("UNIDADES");
        dto.setIngredientes(List.of(ingFail));

        // When + Then
        assertThrows(ElementoNoEncontradoException.class, () -> recetaService.crearReceta(dto));
    }

    @Test
    @DisplayName("3 Filtrar recetas -> Caso positivo: sin filtros devuelve todas")
    void filtrarRecetasPositivoTest() {
        // Given
        String categoria = "   ";
        List<PreferenciaAlimentaria> preferencias = Collections.emptyList(); // sinPreferencias = true

        // When
        List<RecetaResponseDTO> result = recetaService.filtrarRecetas(categoria, preferencias);

        // Then
        assertNotNull(result);
        assertEquals(3, result.size(), "Debería devolver todas las recetas si no hay filtros");
    }

    @Test
    @DisplayName("3 Filtrar recetas -> Caso Negativo: filtros que no encuentran nada => lista vacía")
    void filtrarRecetasNegativoTest() {
        // Given
        String categoria = "Postre"; // no existe en los datos cargados
        List<PreferenciaAlimentaria> preferencias = List.of(PreferenciaAlimentaria.VEGANO);

        // When
        List<RecetaResponseDTO> resultado = recetaService.filtrarRecetas(categoria, preferencias);

        // Then
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty(), "Con filtros que no coinciden debería devolver lista vacía");
    }

    @Test
    @DisplayName("4 Ver detalle receta -> Caso positivo: devuelve pasos, ingredientes y preferencias")
    void buscarPorIdPositivoTest() {
        // Given

        // When
        RecetaResponseDTO dto = recetaService.buscarPorId(idR1);

        // Then
        assertNotNull(dto, "La respuesta no debería ser nula");
        assertEquals(idR1, dto.getIdReceta(), "El idReceta no coincide");
        assertEquals("Cena vegana 1", dto.getTitulo(), "El título no coincide");
        assertEquals("Cena", dto.getCategoria(), "La categoría no coincide");
        assertEquals(Dificultad.FACIL, dto.getDificultad(), "La dificultad no coincide");
        assertEquals(10, dto.getTiempoPreparacion(), "El tiempo de preparación no coincide");

        // Preferencias
        assertNotNull(dto.getPreferencias(), "Preferencias no debería ser null");
        assertTrue(dto.getPreferencias().contains(PreferenciaAlimentaria.VEGANO),
                "Debería incluir preferencia VEGANO");

        // Pasos
        assertNotNull(dto.getPasos(), "Pasos no debería ser null");
        assertEquals(2, dto.getPasos().size(), "Debería devolver 2 pasos");
        assertTrue(dto.getPasos().stream().anyMatch(p -> p.getOrden() == 1 && "Cocer arroz".equals(p.getDescripcion())));
        assertTrue(dto.getPasos().stream().anyMatch(p -> p.getOrden() == 2 && "Saltear tofu".equals(p.getDescripcion())));

        // Ingredientes
        assertNotNull(dto.getIngredientes(), "Ingredientes no debería ser null");
        assertEquals(2, dto.getIngredientes().size(), "Debería devolver 2 ingredientes");

        assertTrue(dto.getIngredientes().stream().anyMatch(ri ->
                ri.getIngrediente() != null &&
                        idIng1.equals(ri.getIngrediente().getIdIngrediente()) &&
                        Double.valueOf(150.0).equals(ri.getCantidad()) &&
                        UnidadMedida.GRAMO.equals(ri.getUnidad())
        ));


        assertTrue(dto.getIngredientes().stream().anyMatch(ri ->
                ri.getIngrediente() != null &&
                        idIng2.equals(ri.getIngrediente().getIdIngrediente()) &&
                        Double.valueOf(200.0).equals(ri.getCantidad()) &&
                        UnidadMedida.GRAMO.equals(ri.getUnidad())
        ));
    }

    @Test
    @DisplayName("4 Ver detalle receta -> Caso negativo: id inexistente lanza excepción")
    void buscarPorIdNegativoTest() {
        // Given
        Long idInexistente = 999999L;

        // Then + When
        assertThrows(ElementoNoEncontradoException.class,
                () -> recetaService.buscarPorId(idInexistente));
    }


}
