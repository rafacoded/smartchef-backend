package com.smartchef.controller;

import com.smartchef.dto.ColeccionRecetaDTO;
import com.smartchef.mapper.ColeccionRecetaMapper;
import com.smartchef.model.ColeccionReceta;
import com.smartchef.service.ColeccionRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colecciones")
@CrossOrigin(origins = "*")
public class ColeccionRecetaController {

    private final ColeccionRecetaService coleccionService;
    private final ColeccionRecetaMapper coleccionMapper;

    @Autowired
    public ColeccionRecetaController(ColeccionRecetaService coleccionService, ColeccionRecetaMapper coleccionMapper) {
        this.coleccionService = coleccionService;
        this.coleccionMapper = coleccionMapper;
    }

    // 📋 Listar colecciones de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<ColeccionRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return coleccionService.listarPorUsuario(idUsuario)
                .stream()
                .map(coleccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔍 Obtener una colección por ID
    @GetMapping("/{idColeccion}")
    public ColeccionRecetaDTO obtenerPorId(@PathVariable Long idColeccion) {
        return coleccionService.obtenerPorId(idColeccion)
                .map(coleccionMapper::toDTO)
                .orElse(null);
    }

    // ➕ Crear o actualizar colección
    @PostMapping
    public ColeccionRecetaDTO guardar(@RequestBody ColeccionRecetaDTO dto) {
        ColeccionReceta coleccion = coleccionMapper.toEntity(dto);
        ColeccionReceta guardada = coleccionService.guardar(coleccion);
        return coleccionMapper.toDTO(guardada);
    }

    // ❌ Eliminar colección
    @DeleteMapping("/{idColeccion}")
    public void eliminar(@PathVariable Long idColeccion) {
        coleccionService.eliminar(idColeccion);
    }
}
