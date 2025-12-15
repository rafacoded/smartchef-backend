package com.smartchef.mapper;

import com.smartchef.dto.RecetaDTO;
import com.smartchef.dto.RecetaResponseDTO;
import com.smartchef.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring", uses = { PasoRecetaMapper.class })
public interface RecetaMapper {

    RecetaResponseDTO toResponseDTO(Receta entity);

    @Mapping(target = "pasos", ignore = true)
    @Mapping(target = "idReceta",  ignore = true)
    Receta toEntity(RecetaDTO dto);


    List<RecetaResponseDTO> toResponseList(List<Receta> entities);

}
