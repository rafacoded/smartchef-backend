package com.smartchef.service;

import com.smartchef.model.ListaCompra;
import com.smartchef.repository.ListaCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaCompraService {

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    public List<ListaCompra> listarPorUsuario(Long idUsuario) {
        return listaCompraRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<ListaCompra> listarActivas(Long idUsuario) {
        return listaCompraRepository.findByUsuarioIdUsuarioAndActivaTrue(idUsuario);
    }

    public ListaCompra crearLista(ListaCompra lista) {
        return listaCompraRepository.save(lista);
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
