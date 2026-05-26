package juanca.registroestudiantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import juanca.registroestudiantes.dto.EstudianteRequestDTO;
import juanca.registroestudiantes.dto.NotaDTO;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.service.SistemaAcademico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EstudianteControllerTest {

    private MockMvc mockMvc;
    private SistemaAcademico sistemaMock;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        sistemaMock = mock(SistemaAcademico.class);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new EstudianteController(sistemaMock)).build();
    }

    @Test
    @DisplayName("GET /api/estudiantes/ranking - Debe retornar 200 OK")
    void testRankingOk() throws Exception {
        Estudiante e1 = new Estudiante(1L, "Juan", "IngSoftware");
        when(sistemaMock.generarRanking()).thenReturn(List.of(e1));

        mockMvc.perform(get("/api/estudiantes/ranking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    @DisplayName("GET /api/estudiantes - Debe retornar 200 OK")
    void testListarOk() throws Exception {
        Estudiante e1 = new Estudiante(1L, "Maria", "Artes");
        when(sistemaMock.obtenerTodos()).thenReturn(List.of(e1));

        mockMvc.perform(get("/api/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Maria"));
    }

    @Test
    @DisplayName("POST /api/estudiantes - Debe registrar con exito")
    void testRegistrarOk() throws Exception {
        EstudianteRequestDTO request = new EstudianteRequestDTO("Juan", "IngSoftware");
        Estudiante estudiante = new Estudiante(1L, "Juan", "IngSoftware");

        when(sistemaMock.registrarEstudiante(anyString(), anyString())).thenReturn(estudiante);

        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("GET /api/estudiantes/{id}/promedio - 404 cuando no existe")
    void testPromedioNotFound() throws Exception {
        when(sistemaMock.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/api/estudiantes/99/promedio"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/estudiantes/{id}/notas - Debe asignar una nota correctamente")
    void testAsiganrNotaOk() throws Exception {
        List<NotaDTO> notas = List.of(
                new NotaDTO(4.6)
        );

        mockMvc.perform(post("/api/estudiantes/1/notas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notas)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Se asignaron 1 notas"))
                .andExpect(jsonPath("$.totalNotas").value(1));

        verify(sistemaMock, times(1)).asignarNota(1L, 4.6);

        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);
        verify(sistemaMock, times(1)).asignarNota(eq(1L), captor.capture());
        assertEquals(4.6, captor.getValue());
    }

    @Test
    @DisplayName("POST /api/estudiantes/{id}/notas - Debe asignar varias notas correctamente")
    void testAsiganrMultiplesNotasOk() throws Exception {
        List<NotaDTO> notas = List.of(
                new NotaDTO(4.6),
                new NotaDTO(2.7),
                new NotaDTO(1.5)
        );

        mockMvc.perform(post("/api/estudiantes/1/notas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notas)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Se asignaron 3 notas"))
                .andExpect(jsonPath("$.totalNotas").value(3));

        verify(sistemaMock, times(3)).asignarNota(eq(1L), anyDouble());

        verify(sistemaMock).asignarNota(1L, 4.6);
        verify(sistemaMock).asignarNota(1L, 2.7);
        verify(sistemaMock).asignarNota(1L, 1.5);

        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);
        verify(sistemaMock, times(3)).asignarNota(eq(1L), captor.capture());

        List<Double> notasCapturadas = captor.getAllValues();
        assertTrue(notasCapturadas.containsAll(List.of(4.6, 2.7, 1.5)));
    }

    @Test
    @DisplayName("GET /api/estudiantes/{id}/promedio - Debe retornar promedio")
    void testPromedioOk() throws Exception {
        Estudiante e = new Estudiante(1L, "Juan", "IngSoftware");
        e.agregarNota(4.1);

        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/api/estudiantes/1/promedio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.promedio").value(4.1));
    }

    @Test
    @DisplayName("GET /api/estudiantes/{id}/estado - Debe retornar APROBADO")
    void testEstadoAprobado() throws Exception {
        Estudiante e = mock(Estudiante.class);
        when(e.getId()).thenReturn(1L);
        when(e.estaAprobado()).thenReturn(true);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/api/estudiantes/1/estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("APROBADO"))
                .andExpect(jsonPath("$.aprobado").value(true));
    }

    @Test
    @DisplayName("GET /api/estudiantes/{id}/estado - Debe retornar REPROBADO")
    void testEstadoReprobado() throws Exception {
        Estudiante e = mock(Estudiante.class);
        when(e.getId()).thenReturn(1L);
        when(e.estaAprobado()).thenReturn(false);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/api/estudiantes/1/estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("REPROBADO"))
                .andExpect(jsonPath("$.aprobado").value(false));
    }

    @Test
    @DisplayName("Debe lanzar excepcion cuando el estudiante no existe")
    void testEstudianteNoEncontrado() throws Exception {
        when(sistemaMock.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/api/estudiantes/99/promedio"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/estudiantes/99/estado"))
                .andExpect(status().isNotFound());
    }
}
