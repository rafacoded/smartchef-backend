package com.smartchef.mapper;

import com.smartchef.dto.GuardadoRecetaDTO;
import com.smartchef.model.*;
import org.springframework.stereotype.Component;

@Component
public class GuardadoRecetaMapper {

    public GuardadoRecetaDTO toDTO(GuardadoReceta g) {
        return new GuardadoRecetaDTO(
                g.getIdGuardado(),
                g.getUsuario() != null ? g.getUsuario().getIdUsuario() : null,
                g.getUsuario() != null ? g.getUsuario().getNombre() : null,
                g.getReceta() != null ? g.getReceta().getIdReceta() : null,
                g.getReceta() != null ? g.getReceta().getTitulo() : null,
                g.getFechaGuardado()
        );
    }

    public GuardadoReceta toEntity(GuardadoRecetaDTO dto) {
        GuardadoReceta g = new GuardadoReceta();
        g.setIdGuardado(dto.getIdGuardado());
        g.setFechaGuardado(dto.getFechaGuardado());

        if (dto.getIdUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getIdUsuario());
            g.setUsuario(u);
        }

        if (dto.getIdReceta() != null) {
            Receta r = new Receta();
            r.setIdReceta(dto.getIdReceta());
            g.setReceta(r);
        }

        return g;
    }
}
