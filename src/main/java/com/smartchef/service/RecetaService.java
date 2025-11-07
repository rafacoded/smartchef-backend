package com.smartchef.service;

import com.smartchef.model.Receta;
import com.smartchef.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> listarTodas() {
        return recetaRepository.findAll();
    }

    public Receta guardar(Receta receta) {
        return recetaRepository.save(receta);
    }

    public List<Receta> buscarPorCategoria(String categoria) {
        return recetaRepository.findByCategoria(categoria);
    }

    public List<Receta> buscarPorAutor(Long idUsuario) {
        return recetaRepository.findByAutorIdUsuario(idUsuario);
    }

    public void eliminar(Long id) {
        recetaRepository.deleteById(id);
    }
}
