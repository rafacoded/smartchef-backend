package com.smartchef.service;

import com.smartchef.model.ColeccionReceta;
import com.smartchef.repository.IColeccionRecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ColeccionRecetaService {

    private final IColeccionRecetaRepository coleccionRepository;

    public List<ColeccionReceta> listarPorUsuario(Long idUsuario) {
        return coleccionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Optional<ColeccionReceta> obtenerPorId(Long idColeccion) {
        return coleccionRepository.findById(idColeccion);
    }

    public ColeccionReceta crearColeccionReceta(ColeccionReceta coleccion) {
        return coleccionRepository.save(coleccion);
    }

    public void eliminar(Long idColeccion) {
        coleccionRepository.deleteById(idColeccion);
    }
}
