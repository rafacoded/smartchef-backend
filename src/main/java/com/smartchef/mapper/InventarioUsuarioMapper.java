package com.smartchef.mapper;

import com.smartchef.dto.InventarioUsuarioDTO;
import com.smartchef.model.IngredienteGlobal;
import com.smartchef.model.InventarioUsuario;
import com.smartchef.model.UnidadMedida;
import com.smartchef.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class InventarioUsuarioMapper {

    public InventarioUsuarioDTO toDTO(InventarioUsuario inv) {
        if (inv == null) return null;

        return new InventarioUsuarioDTO(
                inv.getIdInventario(),
                inv.getUsuario() != null ? inv.getUsuario().getIdUsuario() : null,
                inv.getUsuario() != null ? inv.getUsuario().getNombre() : null,
                inv.getIngrediente() != null ? inv.getIngrediente().getIdIngrediente() : null,
                inv.getIngrediente() != null ? inv.getIngrediente().getNombre() : null,
                inv.getCantidad(),
                inv.getUnidad() != null ? inv.getUnidad().name() : null,
                inv.getFechaActualizacion()
        );
    }

    public InventarioUsuario toEntity(InventarioUsuarioDTO dto) {
        if (dto == null) return null;

        InventarioUsuario inv = new InventarioUsuario();
        inv.setIdInventario(dto.getIdInventario());
        inv.setCantidad(dto.getCantidad());
        inv.setFechaActualizacion(dto.getFechaActualizacion());

        if (dto.getUnidad() != null) {
            inv.setUnidad(UnidadMedida.valueOf(dto.getUnidad().toUpperCase()));
        }

        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            inv.setUsuario(usuario);
        }

        if (dto.getIdIngrediente() != null) {
            IngredienteGlobal ing = new IngredienteGlobal();
            ing.setIdIngrediente(dto.getIdIngrediente());
            inv.setIngrediente(ing);
        }

        return inv;
    }
}
