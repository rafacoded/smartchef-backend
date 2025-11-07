package com.smartchef.service;

import com.smartchef.model.GuardadoReceta;
import com.smartchef.repository.GuardadoRecetaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuardadoRecetaService {

    private final GuardadoRecetaRepository repo;

    public GuardadoRecetaService(GuardadoRecetaRepository repo) {
        this.repo = repo;
    }

    public List<GuardadoReceta> listarPorUsuario(Long idUsuario) {
        return repo.findByUsuarioIdUsuario(idUsuario);
    }

    public GuardadoReceta guardar(GuardadoReceta g) {
        return repo.save(g);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
