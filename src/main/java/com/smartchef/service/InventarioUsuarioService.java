package com.smartchef.service;

import com.smartchef.model.InventarioUsuario;
import com.smartchef.repository.InventarioUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioUsuarioService {

    private final InventarioUsuarioRepository inventarioRepository;

    @Autowired
    public InventarioUsuarioService(InventarioUsuarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioUsuario> listarPorUsuario(Long idUsuario) {
        return inventarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public InventarioUsuario guardar(InventarioUsuario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}
