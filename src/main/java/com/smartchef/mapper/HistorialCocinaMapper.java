package com.smartchef.mapper;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.model.HistorialCocina;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class HistorialCocinaMapper {

    public HistorialCocinaDTO toDTO(HistorialCocina historial) {
        if (historial == null) return null;

        return new HistorialCocinaDTO(
                historial.getIdHistorial(),
                historial.getUsuario() != null ? historial.getUsuario().getIdUsuario() : null,
                historial.getUsuario() != null ? historial.getUsuario().getNombre() : null,
                historial.getReceta() != null ? historial.getReceta().getIdReceta() : null,
                historial.getReceta() != null ? historial.getReceta().getTitulo() : null,
                historial.getFechaRealizacion(),
                historial.getComentario(),
                historial.getValoracionPersonal()
        );
    }

    public HistorialCocina toEntity(HistorialCocinaDTO dto) {
        if (dto == null) return null;

        HistorialCocina historial = new HistorialCocina();
        historial.setIdHistorial(dto.getIdHistorial());
        historial.setFechaRealizacion(dto.getFechaRealizacion());
        historial.setComentario(dto.getComentario());
        historial.setValoracionPersonal(dto.getValoracionPersonal());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            historial.setUsuario(usuario);
        }

        if (dto.getIdReceta() != null) {
            Receta receta = new Receta();
            receta.setIdReceta(dto.getIdReceta());
            historial.setReceta(receta);
        }

        return historial;
    }
}
