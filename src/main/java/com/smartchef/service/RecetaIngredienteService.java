package com.smartchef.service;

import com.smartchef.dto.IngredienteUsoDTO;
import com.smartchef.dto.RecetaIngredienteDTO;
import com.smartchef.dto.UsuarioRecetaPopularDTO;
import com.smartchef.exception.SmartChefException;
import com.smartchef.exception.ValorNoValidoException;
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
        List<Object[]> rows = recetaIngredienteRepository.topIngredientes(PageRequest.of(0,5));

        if (rows == null || rows.isEmpty()) {
            return List.of();
        }

        return rows.stream()
                .map(row -> {
                    if (row == null || row.length < 3) {
                        throw new ValorNoValidoException("Resultado inválido en topIngredientes");
                    }
                    try {
                        return new IngredienteUsoDTO(
                                (Long) row[0],
                                (String) row[1],
                                (Long) row[2]
                        );
                    } catch (ClassCastException e) {
                        throw new ValorNoValidoException("Tipos inválidos en topIngredientes");
                    }
                })
                .toList();
    }

    public List<UsuarioRecetaPopularDTO> usuariosConRecetaMasPopular() {

        Optional<Object[]> top = guardadoRecetaService.recetaMasGuardada();

        if (top.isEmpty()) return List.of();

        Long idReceta = (Long) top.get()[0];
        Long vecesGuardada = (Long) top.get()[1];

        List<Object[]> usuarios = guardadoRecetaService.usuariosQueGuardaronReceta(idReceta);
        if (usuarios == null || usuarios.isEmpty()) return List.of();

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
