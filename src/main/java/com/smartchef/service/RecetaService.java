package com.smartchef.service;

import com.smartchef.dto.*;
import com.smartchef.exception.ElementoNoEncontradoException;
import com.smartchef.mapper.RecetaMapper;
import com.smartchef.model.*;
import com.smartchef.repository.IRecetaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final IngredienteGlobalService ingredienteService;
    private final IRecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;

    @Transactional
    public RecetaResponseDTO crearReceta(RecetaCrearDTO dto) {

        Receta receta = Receta.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .tiempoPreparacion(dto.getTiempoPreparacion())
                .dificultad(dto.getDificultad())
                .categoria(dto.getCategoria())
                .imagen(dto.getImagen())
                .preferencias(dto.getPreferencias())
                .build();

        aplicarPasos(receta, dto.getPasos());
        aplicarIngredientes(receta, dto.getIngredientes());

        return recetaMapper.toResponseDTO(recetaRepository.save(receta));
    }



//    public RecetaResponseDTO crearReceta(RecetaDTO dto) {
//        Receta receta = recetaMapper.toEntity(dto);
//        aplicarPasos(receta, dto.getPasos());
//
//        Receta guardado = recetaRepository.save(receta);
//        return recetaMapper.toResponseDTO(guardado);
//    }

    public List<RecetaResponseDTO> filtrarRecetas(
            String categoria,
            List<PreferenciaAlimentaria> preferencias
    ) {

        boolean sinCategoria = categoria == null || categoria.isBlank();
        boolean sinPreferencias = preferencias == null || preferencias.isEmpty();

        if (sinCategoria && sinPreferencias) {
            return recetaMapper.toResponseList(recetaRepository.findAll());
        }

        return recetaMapper.toResponseList(
                recetaRepository.filtrar(categoria, preferencias)
        );
    }

    public Receta buscarEntityPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow( () ->
                        new ElementoNoEncontradoException(
                                "Receta no encontrada ",
                                "No existe una receta con id " + id
                        )
                );
    }

    public RecetaResponseDTO buscarPorId(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() ->
                        new ElementoNoEncontradoException("Receta no encontrada",
                                "No existe una receta con id " + id)
                );
        return recetaMapper.toResponseDTO(receta);
    }


    public List<RecetaResponseDTO> buscarPorCategoria(String categoria) {
        return recetaRepository.findByCategoriaIgnoreCase(categoria)
                .stream()
                .map(recetaMapper::toResponseDTO)
                .toList();
    }

    public RecetaResponseDTO actualizarReceta(Long id, RecetaDTO dto) {
        Receta existente = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + dto.getIdReceta()));

        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.getPasos().clear();
        aplicarPasos(existente, dto.getPasos());
        existente.setTiempoPreparacion(dto.getTiempoPreparacion());
        existente.setDificultad(Dificultad.valueOf(dto.getDificultad()));
        existente.setCategoria(dto.getCategoria());
        existente.setImagen(dto.getImagen());

        Receta  actualizado = recetaRepository.save(existente);

        return recetaMapper.toResponseDTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: la receta no existe con ID " + id);
        }
        recetaRepository.deleteById(id);
    }

    // ------- UTILS -------

    private void aplicarPasos(Receta receta, List<PasoRecetaDTO> pasosDto) {

        if (receta.getPasos() == null) {
            receta.setPasos(new ArrayList<>());
        }

        receta.getPasos().clear();

        if (pasosDto == null) return;

        for (PasoRecetaDTO dto : pasosDto) {
            PasoReceta paso = PasoReceta.builder()
                    .orden(dto.getOrden())
                    .descripcion(dto.getDescripcion())
                    .receta(receta)
                    .build();

            receta.getPasos().add(paso);
        }
    }

    private void aplicarIngredientes(
            Receta receta,
            List<RecetaIngredienteDTO> ingredientesDto
    ) {

        if (receta.getIngredientes() == null) {
            receta.setIngredientes(new ArrayList<>());
        }

        receta.getIngredientes().clear();

        if (ingredientesDto == null) return;

        for (RecetaIngredienteDTO dto : ingredientesDto) {

            IngredienteGlobal ingrediente =
                    ingredienteService.buscarEntityPorId(dto.getIdIngrediente());

            RecetaIngrediente ri = RecetaIngrediente.builder()
                    .receta(receta)
                    .ingrediente(ingrediente)
                    .cantidad(dto.getCantidad())
                    .unidad(UnidadMedida.valueOf(dto.getUnidad()))
                    .build();

            receta.getIngredientes().add(ri);
        }
    }



}
