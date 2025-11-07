package com.smartchef.service;

import com.smartchef.model.HistorialCocina;
import com.smartchef.repository.HistorialCocinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistorialCocinaService {

    private final HistorialCocinaRepository historialRepository;

    @Autowired
    public HistorialCocinaService(HistorialCocinaRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<HistorialCocina> listarPorUsuario(Long idUsuario) {
        return historialRepository.findByUsuarioIdUsuarioOrderByFechaRealizacionDesc(idUsuario);
    }

    public List<HistorialCocina> listarPorFecha(Long idUsuario, LocalDate fecha) {
        return historialRepository.findByUsuarioIdUsuarioAndFechaRealizacion(idUsuario, fecha);
    }

    public HistorialCocina guardar(HistorialCocina historial) {
        return historialRepository.save(historial);
    }

    public void eliminar(Long idHistorial) {
        historialRepository.deleteById(idHistorial);
    }
}
