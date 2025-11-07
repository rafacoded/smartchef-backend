package com.smartchef.controller;

import com.smartchef.model.IngredienteGlobal;
import com.smartchef.service.IngredienteGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteGlobalController {

    @Autowired
    private IngredienteGlobalService ingredienteService;

    @GetMapping
    public List<IngredienteGlobal> listarIngredientes() {
        return ingredienteService.listarTodos();
    }

    @PostMapping
    public IngredienteGlobal crearIngrediente(@RequestBody IngredienteGlobal ingrediente) {
        return ingredienteService.guardar(ingrediente);
    }

    @GetMapping("/categoria/{categoria}")
    public List<IngredienteGlobal> buscarPorCategoria(@PathVariable String categoria) {
        return ingredienteService.buscarPorCategoria(categoria);
    }

    @GetMapping("/nombre/{nombre}")
    public IngredienteGlobal buscarPorNombre(@PathVariable String nombre) {
        return ingredienteService.buscarPorNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public void eliminarIngrediente(@PathVariable Long id) {
        ingredienteService.eliminar(id);
    }
}
