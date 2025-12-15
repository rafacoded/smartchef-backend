package com.smartchef.controller;

import com.smartchef.dto.PasoRecetaDTO;
import com.smartchef.dto.PasoRecetaResponseDTO;
import com.smartchef.service.PasoRecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas/{idReceta}/pasos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PasoRecetaController {

    private final PasoRecetaService pasoService;

    @GetMapping
    public List<PasoRecetaResponseDTO> listarPasos(@PathVariable Long idReceta) {
        return pasoService.obtenerPasosDeReceta(idReceta);
    }

    @PostMapping
    public PasoRecetaResponseDTO agregarPaso(
            @PathVariable Long idReceta,
            @Valid @RequestBody PasoRecetaDTO dto
    ) {
        return pasoService.agregarPaso(idReceta, dto);
    }
}
