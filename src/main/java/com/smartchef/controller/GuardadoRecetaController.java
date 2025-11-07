package com.smartchef.controller;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.mapper.GuardadoRecetaMapper;
import com.smartchef.model.GuardadoReceta;
import com.smartchef.service.GuardadoRecetaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guardados")
@CrossOrigin(origins = "*")
public class GuardadoRecetaController {

    private final GuardadoRecetaService service;
    private final GuardadoRecetaMapper mapper;

    public GuardadoRecetaController(GuardadoRecetaService service, GuardadoRecetaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<GuardadoRecetaDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GuardadoRecetaDTO guardar(@RequestBody GuardadoRecetaDTO dto) {
        GuardadoReceta g = mapper.toEntity(dto);
        return mapper.toDTO(service.guardar(g));
    }

    @DeleteMapping("/{idGuardado}")
    public void eliminar(@PathVariable Long idGuardado) {
        service.eliminar(idGuardado);
    }
}
