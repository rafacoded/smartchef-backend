package com.smartchef.mapper;

import com.smartchef.dto.ValoracionRecetaDTO;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import com.smartchef.model.ValoracionReceta;
import org.springframework.stereotype.Component;

@Component
public class ValoracionRecetaMapper {

    public ValoracionRecetaDTO toDTO(ValoracionReceta valoracion) {
        if (valoracion == null) return null;

        return new ValoracionRecetaDTO(
                valoracion.getIdValoracion(),
                valoracion.getUsuario() != null ? valoracion.getUsuario().getIdUsuario() : null,
                valoracion.getUsuario() != null ? valoracion.getUsuario().getNombre() : null,
                valoracion.getReceta() != null ? valoracion.getReceta().getIdReceta() : null,
                valoracion.getReceta() != null ? valoracion.getReceta().getTitulo() : null,
                valoracion.getPuntuacion(),
                valoracion.getComentario(),
                valoracion.getFechaValoracion()
        );
    }

    public ValoracionReceta toEntity(ValoracionRecetaDTO dto) {
        if (dto == null) return null;

        ValoracionReceta valoracion = new ValoracionReceta();
        valoracion.setIdValoracion(dto.getIdValoracion());
        valoracion.setPuntuacion(dto.getPuntuacion());
        valoracion.setComentario(dto.getComentario());
        valoracion.setFechaValoracion(dto.getFechaValoracion());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            valoracion.setUsuario(usuario);
        }

        if (dto.getIdReceta() != null) {
            Receta receta = new Receta();
            receta.setIdReceta(dto.getIdReceta());
            valoracion.setReceta(receta);
        }

        return valoracion;
    }
}
