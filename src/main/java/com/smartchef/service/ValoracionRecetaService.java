package com.smartchef.service;

import com.smartchef.model.ValoracionReceta;
import com.smartchef.repository.IValoracionRecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ValoracionRecetaService {

    private final IValoracionRecetaRepository valoracionRepository;

    public List<ValoracionReceta> listarPorReceta(Long idReceta) {
        return valoracionRepository.findByRecetaIdReceta(idReceta);
    }

    public List<ValoracionReceta> listarPorUsuario(Long idUsuario) {
        return valoracionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public ValoracionReceta crearValoracionReceta(ValoracionReceta valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public void eliminar(Long idValoracion) {
        valoracionRepository.deleteById(idValoracion);
    }
}
