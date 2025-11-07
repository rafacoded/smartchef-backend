package com.smartchef.service;

import com.smartchef.model.RecetaIngrediente;
import com.smartchef.repository.RecetaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaIngredienteService {

    private final RecetaIngredienteRepository recetaIngredienteRepository;

    @Autowired
    public RecetaIngredienteService(RecetaIngredienteRepository recetaIngredienteRepository) {
        this.recetaIngredienteRepository = recetaIngredienteRepository;
    }

    public List<RecetaIngrediente> listarPorReceta(Long idReceta) {
        return recetaIngredienteRepository.findByRecetaIdReceta(idReceta);
    }

    public RecetaIngrediente guardar(RecetaIngrediente ri) {
        return recetaIngredienteRepository.save(ri);
    }

    public void eliminar(Long idRecetaIngrediente) {
        recetaIngredienteRepository.deleteById(idRecetaIngrediente);
    }
}
