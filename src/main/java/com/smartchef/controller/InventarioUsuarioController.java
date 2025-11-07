package com.smartchef.controller;

import com.smartchef.dto.InventarioUsuarioDTO;
import com.smartchef.mapper.InventarioUsuarioMapper;
import com.smartchef.model.InventarioUsuario;
import com.smartchef.service.InventarioUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioUsuarioController {

    private final InventarioUsuarioService inventarioService;
    private final InventarioUsuarioMapper inventarioMapper;

    @Autowired
    public InventarioUsuarioController(InventarioUsuarioService inventarioService, InventarioUsuarioMapper inventarioMapper) {
        this.inventarioService = inventarioService;
        this.inventarioMapper = inventarioMapper;
    }

    // 🧾 Listar inventario de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<InventarioUsuarioDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return inventarioService.listarPorUsuario(idUsuario)
                .stream()
                .map(inventarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Añadir o actualizar producto
    @PostMapping
    public InventarioUsuarioDTO guardar(@RequestBody InventarioUsuarioDTO dto) {
        InventarioUsuario inv = inventarioMapper.toEntity(dto);
        InventarioUsuario guardado = inventarioService.guardar(inv);
        return inventarioMapper.toDTO(guardado);
    }

    // ❌ Eliminar producto del inventario
    @DeleteMapping("/{idInventario}")
    public void eliminar(@PathVariable Long idInventario) {
        inventarioService.eliminar(idInventario);
    }
}
