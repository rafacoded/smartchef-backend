package com.smartchef.controller;

import com.smartchef.dto.IngredienteUsoDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.UsuarioRecetaPopularDTO;
import com.smartchef.service.RecetaIngredienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receta-ingredientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RecetaIngredienteController {

    private final RecetaIngredienteService recetaIngredienteService;

    @PostMapping
    public RecetaIngredienteDTO crearRecetaIngrediente(@Valid @RequestBody RecetaIngredienteDTO dto) {
        return recetaIngredienteService.crearRecetaIngrediente(dto);
    }

    @GetMapping("/receta/{idReceta}")
    public List<RecetaIngredienteDTO> listarPorReceta(@PathVariable Long idReceta) {
        return recetaIngredienteService.listarPorReceta(idReceta);
    }

    @DeleteMapping("/{idRecetaIngrediente}")
    public void eliminar(@PathVariable Long idRecetaIngrediente) {
        recetaIngredienteService.eliminar(idRecetaIngrediente);
    }

    // REQUEST 9
    @GetMapping("/top5")
    public List<IngredienteUsoDTO> topIngredientes() {
        return recetaIngredienteService.top5Ingredientes();
    }

    // REQUEST 10
    @GetMapping("/usuario-popular")
    public List<UsuarioRecetaPopularDTO> usuarioPopular() {
        return recetaIngredienteService.usuariosConRecetaMasPopular();
    }
}
