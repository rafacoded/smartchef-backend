package com.smartchef.controller;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.mapper.UsuarioMapper;
import com.smartchef.model.Usuario;
import com.smartchef.security.JwtService;
import com.smartchef.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService,
                          UsuarioService usuarioService,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public UsuarioResponseDTO register(@Valid @RequestBody UsuarioDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Usuario usuario = usuarioService.buscarEntityPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User.withUsername(email)
                        .password(usuario.getPassword())
                        .roles("USER")
                        .build()
        );

        return Map.of("token", token);
    }
}
