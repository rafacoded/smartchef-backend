package com.smartchef.service;

import com.smartchef.dto.HistorialCocinaDTO;
import com.smartchef.dto.HistorialCocinaResponseDTO;
import com.smartchef.exception.OperacionNoPermitidaException;
import com.smartchef.exception.ValorNoValidoException;
import com.smartchef.mapper.HistorialCocinaMapper;
import com.smartchef.model.HistorialCocina;
import com.smartchef.model.Receta;
import com.smartchef.model.Usuario;
import com.smartchef.repository.IHistorialCocinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HistorialCocinaService {

    private final IHistorialCocinaRepository repo;
    private final UsuarioService usuarioService;
    private final RecetaService recetaService;
    private final HistorialCocinaMapper mapper;

    public HistorialCocinaResponseDTO registrar(Long idUsuario, Long idReceta, HistorialCocinaDTO dto) {

        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        Receta receta = recetaService.buscarEntityPorId(idReceta);

        LocalDate fecha = dto.getFechaRealizacion();

        if (repo.existsByUsuarioAndRecetaAndFechaRealizacion(usuario, receta, fecha)) {
            throw new OperacionNoPermitidaException(
                    "Receta ya registrada ese día",
                    "No puedes registrar la misma receta dos veces en la misma fecha"
            );
        }

        HistorialCocina historial = HistorialCocina.builder()
                .usuario(usuario)
                .receta(receta)
                .fechaRealizacion(fecha)
                .comentario(dto.getComentario())
                .valoracionPersonal(dto.getValoracionPersonal())
                .build();

        return mapper.toResponseDTO(repo.save(historial));
    }

    public List<HistorialCocinaResponseDTO> obtenerHistorialSemanal(
            Long idUsuario,
            LocalDate fecha
    ) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new ValorNoValidoException("idUsuario inválido");
        }

        usuarioService.buscarPorId(idUsuario);

        LocalDate base = (fecha != null) ? fecha : LocalDate.now();
        LocalDate inicioSemana = base.with(DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        List<HistorialCocina> rows = repo.findHistorialSemanal(idUsuario, inicioSemana, finSemana);

        if (rows == null || rows.isEmpty()) return List.of();

        return rows.stream().map(mapper::toResponseDTO).toList();
    }


    public List<HistorialCocina> listarPorUsuario(Long idUsuario) {
        return repo.findByUsuarioIdUsuarioOrderByFechaRealizacionDesc(idUsuario);
    }

    public List<HistorialCocina> listarPorFecha(Long idUsuario, LocalDate fecha) {
        return repo.findByUsuarioIdUsuarioAndFechaRealizacion(idUsuario, fecha);
    }

    public void eliminar(Long idHistorial) {
        repo.deleteById(idHistorial);
    }
}
