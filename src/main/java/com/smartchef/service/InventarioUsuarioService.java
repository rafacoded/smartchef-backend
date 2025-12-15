package com.smartchef.service;

import com.smartchef.model.InventarioUsuario;
import com.smartchef.repository.IInventarioUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventarioUsuarioService {

    private final IInventarioUsuarioRepository inventarioRepository;

    public InventarioUsuario crearInventarioUsuario(InventarioUsuario inventario) {
        return inventarioRepository.save(inventario);
    }

    public InventarioUsuario actualizar(InventarioUsuario inventarioActualizado) {
        InventarioUsuario existente = inventarioRepository.findById(inventarioActualizado.getIdInventario())
                .orElseThrow(() ->
                        new RuntimeException("Inventario no encontrado con ID: " + inventarioActualizado.getIdInventario()));

        existente.setCantidad(inventarioActualizado.getCantidad());
        existente.setUnidad(inventarioActualizado.getUnidad());
        existente.setIngrediente(inventarioActualizado.getIngrediente());
        existente.setUsuario(inventarioActualizado.getUsuario());
        existente.setFechaActualizacion(inventarioActualizado.getFechaActualizacion());

        return inventarioRepository.save(existente);
    }

    public List<InventarioUsuario> listarPorUsuario(Long idUsuario) {
        return inventarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public void eliminar(Long idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}
