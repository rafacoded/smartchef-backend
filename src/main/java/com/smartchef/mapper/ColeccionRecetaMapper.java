package com.smartchef.mapper;

import com.smartchef.dto.ColeccionRecetaDTO;
import com.smartchef.model.ColeccionReceta;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel="spring")
public interface ColeccionRecetaMapper {

    ColeccionReceta toEntity(ColeccionRecetaDTO coleccionRecetaDTO);

    ColeccionRecetaDTO toDTO(ColeccionReceta coleccionReceta);

    @Named("convertDate")
    default String dateToString(LocalDateTime fechaCreacion) {
        return fechaCreacion.toString();
    }
}


