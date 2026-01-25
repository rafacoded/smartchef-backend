package com.smartchef.service.unitary;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.dto.ListaCompraResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.model.*;
import com.smartchef.repository.*;
import com.smartchef.service.ListaCompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ListaCompraServiceTest {

    @Autowired
    private ListaCompraService listaCompraService;

    @Autowired private IListaCompraRepository listaCompraRepo;
    @Autowired private IListaItemRepository listaItemRepo;

    @Autowired private IUsuarioRepository usuarioRepo;
    @Autowired private IRecetaRepository recetaRepo;
    @Autowired private IIngredienteGlobalRepository ingredienteRepo;

    private Long usuarioId;
    private Long recetaId;

    @BeforeEach
    void cargar() {
        listaItemRepo.deleteAll();
        listaCompraRepo.deleteAll();
        recetaRepo.deleteAll();
        ingredienteRepo.deleteAll();
        usuarioRepo.deleteAll();

        // Usuario
        Usuario u = new Usuario();
        u.setNombre("user_test");
        u.setEmail("user@test.com");
        u.setPassword("1234");
        u.setPreferenciasAlimentarias(new ArrayList<>());
        u = usuarioRepo.save(u);
        usuarioId = u.getIdUsuario();

        // Ingredientes globales
        IngredienteGlobal ing1 = new IngredienteGlobal();
        ing1.setNombre("Leche");
        ing1.setCategoria("lacteo");
        ing1.setUnidadBase(UnidadMedida.MILILITRO);
        ing1 = ingredienteRepo.save(ing1);

        IngredienteGlobal ing2 = new IngredienteGlobal();
        ing2.setNombre("Harina");
        ing2.setCategoria("grano");
        ing2.setUnidadBase(UnidadMedida.GRAMO);
        ing2 = ingredienteRepo.save(ing2);

        // Receta con ingredientes
        Receta r = new Receta();
        r.setTitulo("Crepes");
        r.setTiempoPreparacion(10);
        r.setDificultad(Dificultad.FACIL);
        r.setPreferencias(new ArrayList<>());

        r.getIngredientes().add(RecetaIngrediente.builder()
                .receta(r)
                .ingrediente(ing1)
                .cantidad(200.0)
                .unidad(UnidadMedida.MILILITRO)
                .build());

        r.getIngredientes().add(RecetaIngrediente.builder()
                .receta(r)
                .ingrediente(ing2)
                .cantidad(100.0)
                .unidad(UnidadMedida.GRAMO)
                .build());

        r = recetaRepo.save(r);
        recetaId = r.getIdReceta();
    }

    @Test
    @DisplayName("6 Generar lista compra -> Caso positivo")
    void generarListaCompraPositivoTest() {
        // Given
        ListaCompraDTO dto = new ListaCompraDTO();
        dto.setNombreLista("Lista crepes");
        dto.setDescripcion("Compra semanal");

        // When
        ListaCompraResponseDTO response =
                listaCompraService.generarDesdeReceta(usuarioId, recetaId, dto);

        // Then (respuesta)
        assertNotNull(response, "La respuesta no debería ser nula");

        // Then (BD)
        assertEquals(1, listaCompraRepo.count(), "Debería guardarse 1 lista de compra");
        assertEquals(2, listaItemRepo.count(), "Deberían guardarse 2 items");

        ListaCompra lista = listaCompraRepo.findAll().getFirst();
        assertEquals("Lista crepes", lista.getNombreLista());
        assertEquals("Compra semanal", lista.getDescripcion());
        assertTrue(lista.getActiva(), "La lista debería estar activa");
        assertEquals(usuarioId, lista.getUsuario().getIdUsuario());
        assertEquals(recetaId, lista.getOrigenReceta().getIdReceta());

        List<ListaItem> items = listaItemRepo.findByListaCompraIdLista(lista.getIdLista());
        assertEquals(2, items.size());

        assertTrue(items.stream().allMatch(i -> Boolean.FALSE.equals(i.getComprado())),
                "Todos los items deberían inicializar con comprado = false");

        assertTrue(items.stream().anyMatch(i ->
                "Leche".equals(i.getIngrediente().getNombre()) &&
                        Double.valueOf(200.0).equals(i.getCantidad()) &&
                        UnidadMedida.MILILITRO.equals(i.getUnidad())
        ));

        assertTrue(items.stream().anyMatch(i ->
                "Harina".equals(i.getIngrediente().getNombre()) &&
                        Double.valueOf(100.0).equals(i.getCantidad()) &&
                        UnidadMedida.GRAMO.equals(i.getUnidad())
        ));
    }

    @Test
    @DisplayName("6 Generar lista compra -> Caso negativo (usuario inexistente)")
    void generarListaCompraNegativoTest() {
        // Given
        ListaCompraDTO dto = new ListaCompraDTO();
        dto.setNombreLista("Lista fail");
        dto.setDescripcion("Debe fallar");

        Long usuarioInexistente = 999999L;

        // When + Then
        assertThrows(ElementoNoEncontradoException.class,
                () -> listaCompraService.generarDesdeReceta(usuarioInexistente, recetaId, dto));
    }
}
