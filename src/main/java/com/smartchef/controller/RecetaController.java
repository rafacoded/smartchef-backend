package com.smartchef.controller;

import com.smartchef.dto.RecetaDTO;
import com.smartchef.mapper.RecetaMapper;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import com.smartchef.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
public class RecetaController {

    private final RecetaService recetaService;
    private final RecetaMapper recetaMapper;

    @Autowired
    public RecetaController(RecetaService recetaService, RecetaMapper recetaMapper) {
        this.recetaService = recetaService;
        this.recetaMapper = recetaMapper;
    }

    // 🧾 Listar todas las recetas
    @GetMapping
    public List<RecetaDTO> listarRecetas() {
        return recetaService.listarTodas()
                .stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ➕ Crear receta
    @PostMapping
    public RecetaDTO crearReceta(@RequestBody RecetaDTO recetaDTO) {
        Receta receta = recetaMapper.toEntity(recetaDTO);

        // 🔗 Asociar autor si se incluye idAutor
        if (recetaDTO.getIdAutor() != null) {
            Usuario autor = new Usuario();
            autor.setIdUsuario(recetaDTO.getIdAutor());
            receta.setAutor(autor);
        }

        Receta guardada = recetaService.guardar(receta);
        return recetaMapper.toDTO(guardada);
    }

    // 🔍 Buscar por categoría
    @GetMapping("/categoria/{categoria}")
    public List<RecetaDTO> buscarPorCategoria(@PathVariable String categoria) {
        return recetaService.buscarPorCategoria(categoria)
                .stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔍 Buscar por autor (preparado para futuro)
    @GetMapping("/autor/{idUsuario}")
    public List<RecetaDTO> buscarPorAutor(@PathVariable Long idUsuario) {
        return recetaService.buscarPorAutor(idUsuario)
                .stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ❌ Eliminar receta
    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Long id) {
        recetaService.eliminar(id);
    }
}
