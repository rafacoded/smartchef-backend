package com.smartchef.mapper;

import com.smartchef.dto.RecetaDTO;
import com.smartchef.model.Dificultad;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapper {

    public RecetaDTO toDTO(Receta receta) {
        if (receta == null) return null;

        return new RecetaDTO(
                receta.getIdReceta(),
                receta.getTitulo(),
                receta.getDescripcion(),
                receta.getPasos(),
                receta.getTiempoPreparacion(),
                receta.getDificultad() != null ? receta.getDificultad().name() : null,
                receta.getCategoria(),
                receta.getImagen(),
                receta.getFechaCreacion(),
                receta.getAutor() != null ? receta.getAutor().getIdUsuario() : null
        );
    }

    public Receta toEntity(RecetaDTO dto) {
        if (dto == null) return null;

        Receta receta = new Receta();
        receta.setIdReceta(dto.getIdReceta());
        receta.setTitulo(dto.getTitulo());
        receta.setDescripcion(dto.getDescripcion());
        receta.setPasos(dto.getPasos());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setCategoria(dto.getCategoria());
        receta.setImagen(dto.getImagen());
        receta.setFechaCreacion(dto.getFechaCreacion());

        if (dto.getDificultad() != null) {
            receta.setDificultad(Dificultad.valueOf(dto.getDificultad().toUpperCase()));
        }

        // El autor se asigna aparte, en el controlador (con UsuarioService)
        if (dto.getIdAutor() != null) {
            Usuario autor = new Usuario();
            autor.setIdUsuario(dto.getIdAutor());
            receta.setAutor(autor);
        }

        return receta;
    }
}
