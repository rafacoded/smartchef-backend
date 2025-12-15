package com.smartchef.controller;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.dto.ListaCompraResponseDTO;
import com.smartchef.mapper.ListaCompraMapper;
import com.smartchef.model.ListaCompra;
import com.smartchef.service.ListaCompraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listas-compra")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ListaCompraController {

    private final ListaCompraService listaCompraService;
    private final ListaCompraMapper listaCompraMapper;

    @PostMapping
    public ListaCompraDTO crearLista(@Valid @RequestBody ListaCompraDTO dto) {
        ListaCompra lista = listaCompraMapper.toEntity(dto);
        ListaCompra creada = listaCompraService.crearLista(lista);
        return listaCompraMapper.toDTO(creada);
    }

    @PostMapping("/usuario/{idUsuario}/receta/{idReceta}")
    public ListaCompraResponseDTO generarDesdeReceta(
            @PathVariable Long idUsuario,
            @PathVariable Long idReceta,
            @Valid @RequestBody ListaCompraDTO dto
    ) {
        return listaCompraService.generarDesdeReceta(
                idUsuario,
                idReceta,
                dto
        );
    }

    @GetMapping("/{idLista}")
    public Optional<ListaCompraDTO> obtenerPorId(@PathVariable Long idLista) {
        return listaCompraService.obtenerPorId(idLista)
                .map(listaCompraMapper::toDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ListaCompraDTO actualizarLista(@PathVariable Long id, @Valid @RequestBody ListaCompraDTO listaDTO) {
        ListaCompra lista = listaCompraMapper.toEntity(listaDTO);
        lista.setIdLista(id);
        ListaCompra actualizada = listaCompraService.actualizarLista(lista);
        return listaCompraMapper.toDTO(actualizada);
    }

    @PatchMapping("/{idLista}/estado")
    public void cambiarEstado(@PathVariable Long idLista, @RequestParam boolean activa) {
        listaCompraService.cambiarEstado(idLista, activa);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ListaCompraDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return listaCompraService.listarPorUsuario(idUsuario)
                .stream()
                .map(listaCompraMapper::toDTO)
                .toList();
    }

    @GetMapping("/usuario/{idUsuario}/activas")
    public List<ListaCompraDTO> listarActivas(@PathVariable Long idUsuario) {
        return listaCompraService.listarActivas(idUsuario)
                .stream()
                .map(listaCompraMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/eliminar/{idLista}")
    public void eliminarLista(@PathVariable Long idLista) {
        listaCompraService.eliminarLista(idLista);
    }

}

