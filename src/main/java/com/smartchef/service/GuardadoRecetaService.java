package com.smartchef.service;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.dto.GuardadoRecetaResponseDTO;
import com.smartchef.exception.OperacionNoPermitidaException;
import com.smartchef.mapper.GuardadoRecetaMapper;
import com.smartchef.model.GuardadoReceta;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IGuardadoRecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuardadoRecetaService {

    private final IGuardadoRecetaRepository repo;
    private final UsuarioService usuarioService;
    private final RecetaService recetaService;
    private final GuardadoRecetaMapper mapper;

    public GuardadoRecetaResponseDTO guardarReceta(Long idUsuario, Long idReceta) {

        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        Receta receta = recetaService.buscarEntityPorId(idReceta);

        if (repo.existsByUsuarioIdUsuarioAndRecetaIdReceta(idUsuario, idReceta)) {
            throw new OperacionNoPermitidaException(
                    "La receta ya está guardada",
                    "El usuario ya tiene esta receta guardada"
            );
        }

        GuardadoReceta guardado = GuardadoReceta.builder()
                .usuario(usuario)
                .receta(receta)
                .build();

        return mapper.toResponseDTO(repo.save(guardado));
    }

    public Optional<Object[]> recetaMasGuardada() {
        return repo.recetaMasGuardada(PageRequest.of(0, 1))
                .stream()
                .findFirst();
    }

    public List<Object[]> usuariosQueGuardaronReceta(Long idReceta) {
        return repo.usuariosPorReceta(idReceta);
    }

    public List<GuardadoRecetaResponseDTO> listarPorUsuario(Long idUsuario) {
        return repo.findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
