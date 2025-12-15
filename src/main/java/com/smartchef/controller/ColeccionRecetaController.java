package com.smartchef.controller;

import jakarta.validation.Valid;

import com.smartchef.dto.ColeccionRecetaDTO;
import com.smartchef.mapper.ColeccionRecetaMapper;
import com.smartchef.model.ColeccionReceta;
import com.smartchef.service.ColeccionRecetaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colecciones")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ColeccionRecetaController {

    private final ColeccionRecetaService coleccionService;
    private final ColeccionRecetaMapper coleccionMapper;

    @PostMapping
    public ColeccionRecetaDTO crearColeccionReceta(@Valid @RequestBody ColeccionRecetaDTO dto) {
        ColeccionReceta coleccion = coleccionMapper.toEntity(dto);
        ColeccionReceta guardada = coleccionService.crearColeccionReceta(coleccion);
        return coleccionMapper.toDTO(guardada);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ColeccionRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return coleccionService.listarPorUsuario(idUsuario)
                .stream()
                .map(coleccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{idColeccion}")
    public ColeccionRecetaDTO obtenerPorId(@PathVariable Long idColeccion) {
        return coleccionService.obtenerPorId(idColeccion)
                .map(coleccionMapper::toDTO)
                .orElse(null);
    }

    @DeleteMapping("/{idColeccion}")
    public void eliminar(@PathVariable Long idColeccion) {
        coleccionService.eliminar(idColeccion);
    }
}
