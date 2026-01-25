package com.smartchef.service.integration;

import com.smartchef.dto.HistorialCocinaResponseDTO;
import com.smartchef.mapper.HistorialCocinaMapper;
import com.smartchef.model.HistorialCocina;
import com.smartchef.repository.IHistorialCocinaRepository;
import com.smartchef.service.HistorialCocinaService;
import com.smartchef.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HistorialCocinaServiceIntegrationTest {

    @InjectMocks private HistorialCocinaService service;

    @Mock private IHistorialCocinaRepository repo;

    @Mock private UsuarioService usuarioService;

    @Mock private HistorialCocinaMapper mapper;

    @Test
    @DisplayName("8 Test Integración -> obtenerHistorialSemanal()")
    void obtenerHistorialSemanalIntegrationTest() {

        // Given
        Long idUsuario = 1L;
        LocalDate base = LocalDate.of(2026, 1, 14); // merlina (miercoles)

        LocalDate inicioSemana = base.with(DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        List<HistorialCocina> hist = List.of(new HistorialCocina(), new HistorialCocina());

        Mockito.when(repo.findHistorialSemanal(idUsuario, inicioSemana, finSemana))
                .thenReturn(hist);

        Mockito.when(mapper.toResponseDTO(Mockito.any(HistorialCocina.class)))
                .thenReturn(new HistorialCocinaResponseDTO());

        // Then
        service.obtenerHistorialSemanal(idUsuario, base);

        // When
        Mockito.verify(usuarioService).buscarPorId(idUsuario);
        Mockito.verify(repo).findHistorialSemanal(idUsuario, inicioSemana, finSemana);

        Mockito.verify(mapper, Mockito.times(2)).toResponseDTO(Mockito.any(HistorialCocina.class));
    }
}
