package com.smartchef.controller;

import com.smartchef.dto.ListaItemDTO;
import com.smartchef.mapper.ListaItemMapper;
import com.smartchef.model.ListaItem;
import com.smartchef.service.ListaItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lista-items")
@CrossOrigin(origins = "*")
public class ListaItemController {

    private final ListaItemService service;
    private final ListaItemMapper mapper;

    public ListaItemController(ListaItemService service, ListaItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/lista/{idLista}")
    public List<ListaItemDTO> listarPorLista(@PathVariable Long idLista) {
        return service.listarPorLista(idLista).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ListaItemDTO guardar(@RequestBody ListaItemDTO dto) {
        ListaItem item = mapper.toEntity(dto);
        return mapper.toDTO(service.guardar(item));
    }

    @DeleteMapping("/{idItem}")
    public void eliminar(@PathVariable Long idItem) {
        service.eliminar(idItem);
    }
}
