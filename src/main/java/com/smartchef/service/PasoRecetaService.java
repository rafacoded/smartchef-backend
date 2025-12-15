package com.smartchef.service;

import com.smartchef.dto.PasoRecetaDTO;
import com.smartchef.dto.PasoRecetaResponseDTO;
import com.smartchef.mapper.PasoRecetaMapper;
import com.smartchef.model.PasoReceta;
import com.smartchef.model.Receta;
import com.smartchef.repository.IPasoRecetaRepository;
import com.smartchef.repository.IRecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PasoRecetaService {

    private final IPasoRecetaRepository pasoRecetaRepository;
    private final IRecetaRepository recetaRepository;
    private final PasoRecetaMapper mapper;

    public PasoRecetaResponseDTO agregarPaso(Long idReceta, PasoRecetaDTO dto) {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        PasoReceta paso = mapper.toEntity(dto);
        paso.setReceta(receta);

        PasoReceta guardado = pasoRecetaRepository.save(paso);

        return mapper.toResponseDTO(guardado);
    }

    public List<PasoRecetaResponseDTO> obtenerPasosDeReceta(Long idReceta) {
        List<PasoReceta> pasos = pasoRecetaRepository
                .findByRecetaIdRecetaOrderByOrdenAsc(idReceta);

        return mapper.toResponseList(pasos);
    }


}

