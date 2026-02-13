package com.smartchef.controller;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioRegistroDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.mapper.UsuarioMapper;
import com.smartchef.model.Usuario;
import com.smartchef.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // SE USA USUARIODTO para entrada y RESPONSEDTO para SALIDA (sin password)
    @PostMapping
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/perfil")
    public UsuarioResponseDTO miPerfil(Authentication auth) {
        String email = auth.getName();
        return usuarioService.buscarPorEmail(email);
    }

    @GetMapping("/{email}")
    public UsuarioResponseDTO obtenerUsuarioPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
