package com.smartchef.service;

import com.smartchef.model.IngredienteGlobal;
import com.smartchef.repository.IngredienteGlobalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteGlobalService {

    @Autowired
    private IngredienteGlobalRepository ingredienteRepository;

    public List<IngredienteGlobal> listarTodos() {
        return ingredienteRepository.findAll();
    }

    public IngredienteGlobal guardar(IngredienteGlobal ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public List<IngredienteGlobal> buscarPorCategoria(String categoria) {
        return ingredienteRepository.findByCategoria(categoria);
    }

    public IngredienteGlobal buscarPorNombre(String nombre) {
        return ingredienteRepository.findByNombre(nombre);
    }

    public void eliminar(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
