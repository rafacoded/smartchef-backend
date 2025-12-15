package com.smartchef.controller;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.dto.GuardadoRecetaResponseDTO;
import com.smartchef.mapper.GuardadoRecetaMapper;
import com.smartchef.model.GuardadoReceta;
import com.smartchef.service.GuardadoRecetaService;
import com.smartchef.service.RecetaService;
import com.smartchef.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class GuardadoRecetaController {

    private final GuardadoRecetaService service;
    private final GuardadoRecetaMapper mapper;

    @PostMapping("/usuarios/{idUsuario}/guardado/{idReceta}")
    public GuardadoRecetaResponseDTO guardarReceta(
            @PathVariable Long idUsuario,
            @PathVariable Long idReceta
    ) {
        return service.guardarReceta(idUsuario, idReceta);
    }



    @GetMapping("/usuarios/{idUsuario}/guardado")
    public List<GuardadoRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/usuarios/{idUsuario}/guardado/{idGuardado}")
    public void eliminar(@PathVariable Long idGuardado) {
        service.eliminar(idGuardado);
    }
}
