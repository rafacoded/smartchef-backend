package com.smartchef.mapper;

import com.smartchef.dto.PasoRecetaDTO;
import com.smartchef.dto.PasoRecetaResponseDTO;
import com.smartchef.model.PasoReceta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PasoRecetaMapper {

    PasoReceta toEntity(PasoRecetaDTO dto);

    PasoRecetaResponseDTO toResponseDTO(PasoReceta entity);

    List<PasoReceta> toEntityList(List<PasoRecetaDTO> dtos);

    List<PasoRecetaResponseDTO> toResponseList(List<PasoReceta> entities);
}

