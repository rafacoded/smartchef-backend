package com.smartchef.controller;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.mapper.ListaCompraMapper;
import com.smartchef.model.ListaCompra;
import com.smartchef.model.Usuario;
import com.smartchef.model.Receta;
import com.smartchef.service.ListaCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;
    private final ListaCompraMapper listaCompraMapper;

    @Autowired
    public ListaCompraController(ListaCompraService listaCompraService, ListaCompraMapper listaCompraMapper) {
        this.listaCompraService = listaCompraService;
        this.listaCompraMapper = listaCompraMapper;
    }

    // 🧾 Listar listas por usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<ListaCompraDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return listaCompraService.listarPorUsuario(idUsuario)
                .stream()
                .map(listaCompraMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Crear nueva lista
    @PostMapping
    public ListaCompraDTO crearLista(@RequestBody ListaCompraDTO listaDTO) {
        ListaCompra lista = listaCompraMapper.toEntity(listaDTO);

        if (listaDTO.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(listaDTO.getIdUsuario());
            lista.setUsuario(usuario);
        }

        if (listaDTO.getIdRecetaOrigen() != null) {
            Receta receta = new Receta();
            receta.setIdReceta(listaDTO.getIdRecetaOrigen());
            lista.setOrigenReceta(receta);
        }

        ListaCompra guardada = listaCompraService.crearLista(lista);
        return listaCompraMapper.toDTO(guardada);
    }

    // 🗑️ Eliminar lista
    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Long id) {
        listaCompraService.eliminarLista(id);
    }

    // 🟢 Listar solo las activas
    @GetMapping("/usuario/{idUsuario}/activas")
    public List<ListaCompraDTO> listarActivas(@PathVariable Long idUsuario) {
        return listaCompraService.listarActivas(idUsuario)
                .stream()
                .map(listaCompraMapper::toDTO)
                .collect(Collectors.toList());
    }
}
