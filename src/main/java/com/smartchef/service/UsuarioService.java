package com.smartchef.service;

import com.smartchef.dto.UsuarioDTO;
import com.smartchef.dto.UsuarioResponseDTO;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.mapper.UsuarioMapper;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(guardado);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponseDTO)
                .toList();
    }

    public Usuario buscarPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ElementoNoEncontradoException(
                        "Usuario no encontrado",
                        "No existe un usuario con id " + idUsuario
                    )
                );
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toResponseDTO)
                .orElse(null);
    }

    public Optional<Usuario> buscarEntityPorEmail(String email) {
        return usuarioRepository.findByEmail(email);

    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuarioMapper.updateFromDTO(dto, existente);

        Usuario actualizado = usuarioRepository.save(existente);

        return usuarioMapper.toResponseDTO(actualizado);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No existe el registro Usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);

    }

}
