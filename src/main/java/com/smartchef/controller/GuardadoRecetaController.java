package com.smartchef.controller;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.dto.GuardadoRecetaResponseDTO;
import com.smartchef.mapper.GuardadoRecetaMapper;
import com.smartchef.model.GuardadoReceta;
import com.smartchef.model.Usuario;
import com.smartchef.service.GuardadoRecetaService;
import com.smartchef.service.RecetaService;
import com.smartchef.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
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

    private final UsuarioService usuarioService;

    @PostMapping("/usuarios/{idUsuario}/guardado/{idReceta}")
    public GuardadoRecetaResponseDTO guardarReceta(
            @PathVariable Long idUsuario,
            @PathVariable Long idReceta
    ) {
        return service.guardarReceta(idUsuario, idReceta);
    }

    @GetMapping("/mis-favoritos")
    public List<GuardadoRecetaResponseDTO> listarPorUsuario(Authentication auth) {

        String email = auth.getName();

        Usuario usuario = usuarioService.buscarEntityPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return service.listarPorUsuario(usuario.getIdUsuario());
    }

    @DeleteMapping("/usuarios/{idUsuario}/guardado/{idGuardado}")
    public void eliminar(@PathVariable Long idGuardado) {
        service.eliminar(idGuardado);
    }
}
