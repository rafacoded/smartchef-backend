package com.smartchef.service;

import com.smartchef.dto.IngredienteUsoDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.UsuarioRecetaPopularDTO;
import com.smartchef.mapper.RecetaIngredienteMapper;
import com.smartchef.model.RecetaIngrediente;
import com.smartchef.repository.IRecetaIngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecetaIngredienteService {

    private final IRecetaIngredienteRepository recetaIngredienteRepository;

    private final RecetaService recetaService;

    private final GuardadoRecetaService guardadoRecetaService;

    private final RecetaIngredienteMapper recetaIngredienteMapper;

    public RecetaIngredienteDTO crearRecetaIngrediente(RecetaIngredienteDTO dto) {
        RecetaIngrediente recIng =  recetaIngredienteMapper.toEntity(dto);
        RecetaIngrediente guardado = recetaIngredienteRepository.save(recIng);
        return recetaIngredienteMapper.toDTO(guardado);
    }

    public List<RecetaIngrediente> buscarPorReceta(Long idReceta) {
        recetaService.buscarEntityPorId(idReceta);
        return recetaIngredienteRepository.findByRecetaIdReceta(idReceta);
    }

    public List<RecetaIngredienteDTO> listarPorReceta(Long idReceta) {
        return recetaIngredienteRepository.findByRecetaIdReceta(idReceta)
                .stream()
                .map(recetaIngredienteMapper::toDTO)
                .toList();
    }

    public void eliminar(Long idRecetaIngrediente) {
        if (!recetaIngredienteRepository.existsById(idRecetaIngrediente)) {
            throw new RuntimeException("No existe el registro RecetaIngrediente con ID: " + idRecetaIngrediente);
        }
        recetaIngredienteRepository.deleteById(idRecetaIngrediente);
    }

    // ESTADÍSTICAS
    public List<IngredienteUsoDTO> top5Ingredientes() {
        return recetaIngredienteRepository
                .topIngredientes(PageRequest.of(0,5))
                .stream()
                .map(row -> new IngredienteUsoDTO(
                        (Long) row[0],
                        (String) row[1],
                        (Long) row[2]
                ))
                .toList();
    }

    public List<UsuarioRecetaPopularDTO> usuariosConRecetaMasPopular() {

        Optional<Object[]> top = guardadoRecetaService.recetaMasGuardada();

        if (top.isEmpty()) return List.of();

        Long idReceta = (Long) top.get()[0];
        Long vecesGuardada = (Long) top.get()[1];

        List<Object[]> usuarios = guardadoRecetaService.usuariosQueGuardaronReceta(idReceta);

        return usuarios.stream()
                .map(row -> new UsuarioRecetaPopularDTO(
                        (Long) row[0],
                        (String) row[1],
                        (Long) row[2],
                        (String) row[3],
                        vecesGuardada
                ))
                .toList();
    }
}
