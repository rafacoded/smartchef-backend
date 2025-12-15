package com.smartchef.controller;

import com.smartchef.dto.ValoracionRecetaDTO;
import com.smartchef.mapper.ValoracionRecetaMapper;
import com.smartchef.model.ValoracionReceta;
import com.smartchef.service.ValoracionRecetaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ValoracionRecetaController {

    private final ValoracionRecetaService valoracionService;
    private final ValoracionRecetaMapper valoracionMapper;

    @PostMapping
    public ValoracionRecetaDTO crearValoracionReceta(@Valid @RequestBody ValoracionRecetaDTO dto) {
        ValoracionReceta valoracion = valoracionMapper.toEntity(dto);
        ValoracionReceta guardada = valoracionService.crearValoracionReceta(valoracion);
        return valoracionMapper.toDTO(guardada);
    }

    @GetMapping("/receta/{idReceta}")
    public List<ValoracionRecetaDTO> listarPorReceta(@PathVariable Long idReceta) {
        return valoracionService.listarPorReceta(idReceta)
                .stream()
                .map(valoracionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ValoracionRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return valoracionService.listarPorUsuario(idUsuario)
                .stream()
                .map(valoracionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{idValoracion}")
    public void eliminar(@PathVariable Long idValoracion) {
        valoracionService.eliminar(idValoracion);
    }
}
