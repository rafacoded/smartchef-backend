package com.smartchef.service;

import com.smartchef.dto.ListaCompraDTO;
import com.smartchef.dto.ListaCompraResponseDTO;
import com.smartchef.mapper.ListaCompraMapper;
import com.smartchef.model.*;
import com.smartchef.repository.IListaCompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListaCompraService {

    private IListaCompraRepository listaCompraRepository;
    private UsuarioService usuarioService;
    private RecetaService recetaService;
    private RecetaIngredienteService recetaIngredienteService;
    private ListaItemService listaItemService;
    private ListaCompraMapper listaCompraMapper;

    public ListaCompra crearLista(ListaCompra lista) {
        return listaCompraRepository.save(lista);
    }

    @Transactional
    public ListaCompraResponseDTO generarDesdeReceta(
            Long idUsuario,
            Long idReceta,
            ListaCompraDTO dto
    ) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        Receta receta = recetaService.buscarEntityPorId(idReceta);

        ListaCompra lista = ListaCompra.builder()
                .nombreLista(dto.getNombreLista())
                .descripcion(dto.getDescripcion())
                .usuario(usuario)
                .origenReceta(receta)
                .activa(true)
                .build();

        listaCompraRepository.save(lista);

        List<RecetaIngrediente> ingredientes =
                recetaIngredienteService.buscarPorReceta(idReceta);

        List<ListaItem> items = ingredientes.stream()
                .map(ri -> ListaItem.builder()
                        .listaCompra(lista)
                        .ingrediente(ri.getIngrediente())
                        .cantidad(ri.getCantidad())
                        .unidad(ri.getUnidad())
                        .comprado(false)
                        .build()
                )
                .toList();

        listaItemService.guardarItems(items);

        return listaCompraMapper.toResponseDTO(lista, items);
    }


    public List<ListaCompra> listarPorUsuario(Long idUsuario) {
        return listaCompraRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<ListaCompra> listarActivas(Long idUsuario) {
        return listaCompraRepository.findByUsuarioIdUsuarioAndActivaTrue(idUsuario);
    }

    public Optional<ListaCompra> obtenerPorId(Long idLista) {
        return listaCompraRepository.findById(idLista);
    }

    public ListaCompra actualizarLista(ListaCompra lista) {
        return listaCompraRepository.save(lista);
    }

    public void eliminarLista(Long idLista) {
        listaCompraRepository.deleteById(idLista);
    }

    public void cambiarEstado(Long idLista, boolean activa) {
        listaCompraRepository.findById(idLista).ifPresent(lista -> {
            lista.setActiva(activa);
            listaCompraRepository.save(lista);
        });
    }
}
