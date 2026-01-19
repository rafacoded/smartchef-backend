package com.smartchef.service;

import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.model.Dificultad;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.model.Receta;
import com.smartchef.repository.IRecetaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecetaServiceTest {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IRecetaRepository repository;


    @BeforeAll
    void cargarDatos() {
        Receta receta = new Receta();
        receta.setTitulo("Titulo Receta");
        receta.setImagen("imagen");
        receta.setDescripcion("Descripcion Receta");
        receta.setDificultad(Dificultad.FACIL);
        receta.setIngredientes(new ArrayList<>());
        receta.setPasos(new ArrayList<>());
        receta.setPreferencias(new ArrayList<>());
        receta.setTiempoPreparacion(15);

        Receta receta2 = new Receta();
        receta2.setTitulo("Titulo Receta 2");
        receta2.setImagen("imagen2");
        receta2.setDescripcion("Descripcion Receta 2");
        receta2.setDificultad(Dificultad.MEDIA);
        receta2.setIngredientes(new ArrayList<>());
        receta2.setPasos(new ArrayList<>());
        receta2.setPreferencias(new ArrayList<>());
        receta2.setTiempoPreparacion(25);

        IngredienteGlobal ingGlob1 = new IngredienteGlobal();
        ingGlob1.setNombre("ingGlob1");
        ingGlob1.setCategoria("lacteo");
        IngredienteGlobal ingGlob2 = new IngredienteGlobal();
        ingGlob2.setNombre("fibra");
        IngredienteGlobal ingGlob3 = new IngredienteGlobal();
        ingGlob3.setNombre("carne");

        IngredienteGlobal ingGlob4 = new IngredienteGlobal();
        ingGlob1.setNombre("ingGlob4");
        ingGlob1.setCategoria("verdura");
        IngredienteGlobal ingGlob5 = new IngredienteGlobal();
        ingGlob2.setNombre("ingGlob5");
        ingGlob2.setCategoria("tuberculo");
        IngredienteGlobal ingGlob6 = new IngredienteGlobal();
        ingGlob3.setNombre("ingGlob6");
        ingGlob3.setCategoria("proteico");

        repository.save(receta);
        repository.save(receta2);
    }

    @Test
    public void buscarPorIdTest() {

        //Given

        //Then
        RecetaResponseDTO recetadto = recetaService.buscarPorId(1L);

        //When
        assertNotNull(recetadto, "La receta que se ha intentado buscar no existe o es nulo");
        assertEquals(recetadto.getTitulo(), "Titulo Receta", "El título de la receta no coincide con el buscado");


    }

    @Test
    public void buscarPorIdNegativoTest() {
        //Given

        //Then

        //When
        assertThrows(ElementoNoEncontradoException.class, () -> recetaService.buscarPorId(3L));
    }

    @Test
    @DisplayName("Servicio x -> Caso negativo")
    public void aplicarIngredientesTest() {
        //Given

        //Then

        //When



    }

}
