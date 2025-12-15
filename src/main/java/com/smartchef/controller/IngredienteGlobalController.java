package com.smartchef.controller;

import com.smartchef.dto.IngredienteGlobalDTO;
import com.smartchef.mapper.IngredienteGlobalMapper;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.service.IngredienteGlobalService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class IngredienteGlobalController {

    private IngredienteGlobalService ingredienteService;

    private IngredienteGlobalMapper ingredienteMapper;

    @PostMapping
    public IngredienteGlobalDTO crearIngrediente(@Valid @RequestBody IngredienteGlobalDTO dto) {
        IngredienteGlobal ing = ingredienteMapper.toEntity(dto);
        IngredienteGlobal guardado = ingredienteService.crearIngredienteGlobal(ing);
        return ingredienteMapper.toDTO(guardado);
    }

    @GetMapping
    public List<IngredienteGlobalDTO> listarIngredientes() {
        return ingredienteService.listarTodos()
                .stream()
                .map(ingredienteMapper::toDTO)
                .toList();
    }

    @GetMapping("/categoria/{categoria}")
    public List<IngredienteGlobalDTO> buscarPorCategoria(@PathVariable String categoria) {
        return ingredienteService.buscarPorCategoria(categoria)
                .stream()
                .map(ingredienteMapper::toDTO)
                .toList();
    }

    @GetMapping("/nombre/{nombre}")
    public IngredienteGlobalDTO buscarPorNombre(@PathVariable String nombre) {
        IngredienteGlobal ing = ingredienteService.buscarPorNombre(nombre);
        return ingredienteMapper.toDTO(ing);
    }

    @DeleteMapping("/{id}")
    public void eliminarIngrediente(@PathVariable Long id) {
        ingredienteService.eliminar(id);
    }
}
