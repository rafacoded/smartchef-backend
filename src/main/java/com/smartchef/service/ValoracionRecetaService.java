package com.smartchef.service;

import com.smartchef.model.ValoracionReceta;
import com.smartchef.repository.ValoracionRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionRecetaService {

    private final ValoracionRecetaRepository valoracionRepository;

    @Autowired
    public ValoracionRecetaService(ValoracionRecetaRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<ValoracionReceta> listarPorReceta(Long idReceta) {
        return valoracionRepository.findByRecetaIdReceta(idReceta);
    }

    public List<ValoracionReceta> listarPorUsuario(Long idUsuario) {
        return valoracionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public ValoracionReceta guardar(ValoracionReceta valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public void eliminar(Long idValoracion) {
        valoracionRepository.deleteById(idValoracion);
    }
}
