package com.smartchef.controller;

import com.smartchef.dto.ListaItemDTO;
import com.smartchef.mapper.ListaItemMapper;
import com.smartchef.model.ListaItem;
import com.smartchef.service.ListaItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista-items")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ListaItemController {

    private final ListaItemService service;
    private final ListaItemMapper mapper;

    @PostMapping
    public ListaItemDTO crearListaItem(@Valid @RequestBody ListaItemDTO dto) {
        ListaItem item = mapper.toEntity(dto);

        return mapper.toDTO(item);
    }

    @GetMapping("/lista/{idLista}")
    public List<ListaItemDTO> listarPorListaCompra(@PathVariable Long idLista) {
        return service.listarPorListaCompra(idLista);
    }

    @PutMapping("/{id}")
    public ListaItemDTO actualizarItem(@PathVariable Long id, @Valid @RequestBody ListaItemDTO dto) {
        return service.actualizarItem(id, dto);
    }

    @DeleteMapping("/{idItem}")
    public void eliminar(@PathVariable Long idItem) {
        service.eliminar(idItem);
    }
}
