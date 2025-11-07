package com.smartchef.controller;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.mapper.HistorialCocinaMapper;
import com.smartchef.model.HistorialCocina;
import com.smartchef.service.HistorialCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialCocinaController {

    private final HistorialCocinaService historialService;
    private final HistorialCocinaMapper historialMapper;

    @Autowired
    public HistorialCocinaController(HistorialCocinaService historialService, HistorialCocinaMapper historialMapper) {
        this.historialService = historialService;
        this.historialMapper = historialMapper;
    }

    // 📅 Listar historial completo de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialCocinaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return historialService.listarPorUsuario(idUsuario)
                .stream()
                .map(historialMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 📆 Listar historial de un día específico
    @GetMapping("/usuario/{idUsuario}/fecha/{fecha}")
    public List<HistorialCocinaDTO> listarPorFecha(@PathVariable Long idUsuario, @PathVariable String fecha) {
        LocalDate date = LocalDate.parse(fecha);
        return historialService.listarPorFecha(idUsuario, date)
                .stream()
                .map(historialMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Registrar receta cocinada
    @PostMapping
    public HistorialCocinaDTO guardar(@RequestBody HistorialCocinaDTO dto) {
        HistorialCocina historial = historialMapper.toEntity(dto);
        HistorialCocina guardado = historialService.guardar(historial);
        return historialMapper.toDTO(guardado);
    }

    // ❌ Eliminar registro
    @DeleteMapping("/{idHistorial}")
    public void eliminar(@PathVariable Long idHistorial) {
        historialService.eliminar(idHistorial);
    }
}
