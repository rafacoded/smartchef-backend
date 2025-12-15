package com.smartchef.controller;

import com.smartchef.dto.RecetaCrearDTO;
import com.smartchef.dto.RecetaDTO;
import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.model.PreferenciaAlimentaria;
import com.smartchef.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping
    public RecetaResponseDTO crearReceta(@Valid @RequestBody RecetaCrearDTO recetaDTO) {
        return recetaService.crearReceta(recetaDTO);
    }

    @GetMapping
    public List<RecetaResponseDTO> listarRecetas(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) List<PreferenciaAlimentaria> preferencias
    ) {
        return recetaService.filtrarRecetas(categoria, preferencias);
    }


//    @PutMapping("/{id}")
//    public RecetaResponseDTO actualizarReceta(@PathVariable Long id, @Valid @RequestBody RecetaDTO recetaDTO) {
//        return recetaService.actualizarReceta(id, recetaDTO);
//    }

    @GetMapping("/{id}")
    public RecetaResponseDTO buscarPorId(@PathVariable Long id) {
        return recetaService.buscarPorId(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<RecetaResponseDTO> buscarPorCategoria(@PathVariable String categoria) {
        return recetaService.buscarPorCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Long id) {
        recetaService.eliminar(id);
    }
}
