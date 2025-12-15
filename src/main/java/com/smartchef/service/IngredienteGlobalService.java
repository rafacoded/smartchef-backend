package com.smartchef.service;

import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.repository.IIngredienteGlobalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredienteGlobalService {

    private IIngredienteGlobalRepository ingredienteRepository;

    public IngredienteGlobal crearIngredienteGlobal(IngredienteGlobal ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public List<IngredienteGlobal> listarTodos() {
        return ingredienteRepository.findAll();
    }

    public IngredienteGlobal buscarEntityPorId(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException(
                        "Ingrediente no encontrado",
                        "No existe ingrediente con id " + id
                ));
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
