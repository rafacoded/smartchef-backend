package com.smartchef.controller;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.mapper.UsuarioMapper;
import com.smartchef.model.Usuario;
import com.smartchef.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // permite peticiones del frontend (Angular/Ionic)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    // ✅ Inyección por constructor
    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    // 🧾 Listar todos los usuarios
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    // ➕ Crear un usuario (usando DTO)
    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario guardado = usuarioService.crearUsuario(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    // 🔍 Buscar usuario por email
    @GetMapping("/{email}")
    public Optional<UsuarioDTO> obtenerUsuarioPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(usuarioMapper::toDTO);
    }

    // ❌ Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
