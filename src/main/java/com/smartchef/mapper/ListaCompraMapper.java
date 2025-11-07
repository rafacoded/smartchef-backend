package com.smartchef.mapper;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.model.ListaCompra;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ListaCompraMapper {

    public ListaCompraDTO toDTO(ListaCompra lista) {
        if (lista == null) return null;

        return new ListaCompraDTO(
                lista.getIdLista(),
                lista.getNombreLista(),
                lista.getDescripcion(),
                lista.getActiva(),
                lista.getFechaCreacion(),
                lista.getFechaActualizacion(),
                lista.getUsuario() != null ? lista.getUsuario().getIdUsuario() : null,
                lista.getUsuario() != null ? lista.getUsuario().getNombre() : null,
                lista.getOrigenReceta() != null ? lista.getOrigenReceta().getIdReceta() : null,
                lista.getOrigenReceta() != null ? lista.getOrigenReceta().getTitulo() : null
        );
    }

    public ListaCompra toEntity(ListaCompraDTO dto) {
        if (dto == null) return null;

        ListaCompra lista = new ListaCompra();
        lista.setIdLista(dto.getIdLista());
        lista.setNombreLista(dto.getNombreLista());
        lista.setDescripcion(dto.getDescripcion());
        lista.setActiva(dto.getActiva());
        lista.setFechaCreacion(dto.getFechaCreacion());
        lista.setFechaActualizacion(dto.getFechaActualizacion());

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            lista.setUsuario(usuario);
        }

        if (dto.getIdRecetaOrigen() != null) {
            Receta receta = new Receta();
            receta.setIdReceta(dto.getIdRecetaOrigen());
            lista.setOrigenReceta(receta);
        }

        return lista;
    }
}
