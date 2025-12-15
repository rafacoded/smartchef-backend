package com.smartchef.controller;

import com.smartchef.dto.InventarioUsuarioDTO;
import com.smartchef.mapper.InventarioUsuarioMapper;
import com.smartchef.model.InventarioUsuario;
import com.smartchef.service.InventarioUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class InventarioUsuarioController {

    private final InventarioUsuarioService inventarioService;
    private final InventarioUsuarioMapper inventarioMapper;

    @PostMapping
    public InventarioUsuarioDTO crearInventario(@Valid @RequestBody InventarioUsuarioDTO dto) {
        InventarioUsuario inv = inventarioMapper.toEntity(dto);
        InventarioUsuario guardado = inventarioService.crearInventarioUsuario(inv);
        return inventarioMapper.toDTO(guardado);
    }

    @PutMapping("/{id}")
    public InventarioUsuarioDTO actualizarInventario(@PathVariable Long id, @Valid @RequestBody InventarioUsuarioDTO dto) {
        InventarioUsuario inv = inventarioMapper.toEntity(dto);
        inv.setIdInventario(id);
        InventarioUsuario actualizado = inventarioService.actualizar(inv);
        return inventarioMapper.toDTO(actualizado);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<InventarioUsuarioDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return inventarioService.listarPorUsuario(idUsuario)
                .stream()
                .map(inventarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{idInventario}")
    public void eliminar(@PathVariable Long idInventario) {
        inventarioService.eliminar(idInventario);
    }
}
