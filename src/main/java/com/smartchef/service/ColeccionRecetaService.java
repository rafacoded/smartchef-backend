package com.smartchef.service;

import com.smartchef.model.ColeccionReceta;
import com.smartchef.repository.ColeccionRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColeccionRecetaService {

    private final ColeccionRecetaRepository coleccionRepository;

    @Autowired
    public ColeccionRecetaService(ColeccionRecetaRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    public List<ColeccionReceta> listarPorUsuario(Long idUsuario) {
        return coleccionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Optional<ColeccionReceta> obtenerPorId(Long idColeccion) {
        return coleccionRepository.findById(idColeccion);
    }

    public ColeccionReceta guardar(ColeccionReceta coleccion) {
        return coleccionRepository.save(coleccion);
    }

    public void eliminar(Long idColeccion) {
        coleccionRepository.deleteById(idColeccion);
    }
}
