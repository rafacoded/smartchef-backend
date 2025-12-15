package com.smartchef.controller;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.dto.HistorialCocinaResponseDTO;
import com.smartchef.mapper.HistorialCocinaMapper;
import com.smartchef.service.HistorialCocinaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historial-cocina")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class HistorialCocinaController {

    private final HistorialCocinaService historialCocinaService;
    private final HistorialCocinaMapper historialMapper;

    @PostMapping("/usuarios/{idUsuario}/recetas/{idReceta}")
    public HistorialCocinaResponseDTO guardar(
            @PathVariable Long idUsuario,
            @PathVariable Long idReceta,
            @Valid @RequestBody HistorialCocinaDTO dto
    ) {
        return historialCocinaService.registrar(idUsuario, idReceta, dto);
    }

    @GetMapping("/usuarios/{idUsuario}/semana")
    public List<HistorialCocinaResponseDTO> historialSemanal(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) LocalDate fecha
    ) {
        return historialCocinaService.obtenerHistorialSemanal(idUsuario, fecha);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialCocinaResponseDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return historialCocinaService.listarPorUsuario(idUsuario)
                .stream()
                .map(historialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{idUsuario}/fecha/{fecha}")
    public List<HistorialCocinaResponseDTO> listarPorFecha(@PathVariable Long idUsuario, @PathVariable String fecha) {
        LocalDate date = LocalDate.parse(fecha);
        return historialCocinaService.listarPorFecha(idUsuario, date)
                .stream()
                .map(historialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{idHistorial}")
    public void eliminar(@PathVariable Long idHistorial) {
        historialCocinaService.eliminar(idHistorial);
    }
}
