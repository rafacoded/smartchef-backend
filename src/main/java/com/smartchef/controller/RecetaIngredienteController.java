package com.smartchef.controller;

import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.mapper.RecetaIngredienteMapper;
import com.smartchef.model.RecetaIngrediente;
import com.smartchef.service.RecetaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/receta-ingredientes")
@CrossOrigin(origins = "*")
public class RecetaIngredienteController {

    private final RecetaIngredienteService recetaIngredienteService;
    private final RecetaIngredienteMapper recetaIngredienteMapper;

    @Autowired
    public RecetaIngredienteController(RecetaIngredienteService recetaIngredienteService,
                                       RecetaIngredienteMapper recetaIngredienteMapper) {
        this.recetaIngredienteService = recetaIngredienteService;
        this.recetaIngredienteMapper = recetaIngredienteMapper;
    }

    // 📋 Listar ingredientes de una receta
    @GetMapping("/receta/{idReceta}")
    public List<RecetaIngredienteDTO> listarPorReceta(@PathVariable Long idReceta) {
        return recetaIngredienteService.listarPorReceta(idReceta)
                .stream()
                .map(recetaIngredienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Añadir ingrediente a una receta
    @PostMapping
    public RecetaIngredienteDTO guardar(@RequestBody RecetaIngredienteDTO dto) {
        RecetaIngrediente ri = recetaIngredienteMapper.toEntity(dto);
        RecetaIngrediente guardado = recetaIngredienteService.guardar(ri);
        return recetaIngredienteMapper.toDTO(guardado);
    }

    // ❌ Eliminar ingrediente de una receta
    @DeleteMapping("/{idRecetaIngrediente}")
    public void eliminar(@PathVariable Long idRecetaIngrediente) {
        recetaIngredienteService.eliminar(idRecetaIngrediente);
    }
}
