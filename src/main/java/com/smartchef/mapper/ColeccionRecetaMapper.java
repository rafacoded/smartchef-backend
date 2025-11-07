package com.smartchef.mapper;

import com.smartchef.dto.ColeccionRecetaDTO;
import com.smartchef.model.ColeccionReceta;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ColeccionRecetaMapper {

    public ColeccionRecetaDTO toDTO(ColeccionReceta coleccion) {
        if (coleccion == null) return null;

        return new ColeccionRecetaDTO(
                coleccion.getIdColeccion(),
                coleccion.getUsuario() != null ? coleccion.getUsuario().getIdUsuario() : null,
                coleccion.getUsuario() != null ? coleccion.getUsuario().getNombre() : null,
                coleccion.getNombre(),
                coleccion.getDescripcion(),
                coleccion.getFechaCreacion(),
                coleccion.getRecetas() != null
                        ? coleccion.getRecetas().stream().map(Receta::getIdReceta).collect(Collectors.toSet())
                        : null,
                coleccion.getRecetas() != null
                        ? coleccion.getRecetas().stream().map(Receta::getTitulo).collect(Collectors.toSet())
                        : null
        );
    }

    public ColeccionReceta toEntity(ColeccionRecetaDTO dto) {
        if (dto == null) return null;

        ColeccionReceta coleccion = new ColeccionReceta();
        coleccion.setIdColeccion(dto.getIdColeccion());
        coleccion.setNombre(dto.getNombre());
        coleccion.setDescripcion(dto.getDescripcion());
        coleccion.setFechaCreacion(dto.getFechaCreacion());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            coleccion.setUsuario(usuario);
        }

        if (dto.getIdsRecetas() != null) {
            Set<Receta> recetas = dto.getIdsRecetas().stream().map(id -> {
                Receta receta = new Receta();
                receta.setIdReceta(id);
                return receta;
            }).collect(Collectors.toSet());
            coleccion.setRecetas(recetas);
        }

        return coleccion;
    }
}
