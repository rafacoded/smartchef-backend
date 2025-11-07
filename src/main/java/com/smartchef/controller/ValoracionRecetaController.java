package com.smartchef.controller;

import com.smartchef.dto.ValoracionRecetaDTO;
import com.smartchef.mapper.ValoracionRecetaMapper;
import com.smartchef.model.ValoracionReceta;
import com.smartchef.service.ValoracionRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "*")
public class ValoracionRecetaController {

    private final ValoracionRecetaService valoracionService;
    private final ValoracionRecetaMapper valoracionMapper;

    @Autowired
    public ValoracionRecetaController(ValoracionRecetaService valoracionService, ValoracionRecetaMapper valoracionMapper) {
        this.valoracionService = valoracionService;
        this.valoracionMapper = valoracionMapper;
    }

    // 🧾 Listar valoraciones de una receta
    @GetMapping("/receta/{idReceta}")
    public List<ValoracionRecetaDTO> listarPorReceta(@PathVariable Long idReceta) {
        return valoracionService.listarPorReceta(idReceta)
                .stream()
                .map(valoracionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🧾 Listar valoraciones hechas por un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<ValoracionRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return valoracionService.listarPorUsuario(idUsuario)
                .stream()
                .map(valoracionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Crear / actualizar valoración
    @PostMapping
    public ValoracionRecetaDTO guardar(@RequestBody ValoracionRecetaDTO dto) {
        ValoracionReceta valoracion = valoracionMapper.toEntity(dto);
        ValoracionReceta guardada = valoracionService.guardar(valoracion);
        return valoracionMapper.toDTO(guardada);
    }

    // ❌ Eliminar valoración
    @DeleteMapping("/{idValoracion}")
    public void eliminar(@PathVariable Long idValoracion) {
        valoracionService.eliminar(idValoracion);
    }
}
