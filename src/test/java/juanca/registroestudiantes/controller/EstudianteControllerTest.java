package juanca.registroestudiantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import juanca.registroestudiantes.dto.*;
import juanca.registroestudiantes.model.Estudiante;
import juanca.registroestudiantes.model.SistemaAcademico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

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
    @DisplayName("GET /estudiantes/ranking - Debe retornar 200 OK")
    void testRankingOk() throws Exception {
        Estudiante e1 = new Estudiante(1L, "Juan", "IngSoftware");
        when(sistemaMock.generarRanking()).thenReturn(List.of(e1));

        mockMvc.perform(get("/estudiantes/ranking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    @DisplayName("GET /estudiantes - Debe retornar 200 OK")
    void testListarOk() throws Exception {
        Estudiante e1 = new Estudiante(1L, "Maria", "Artes");
        when(sistemaMock.obtenerTodos()).thenReturn(List.of(e1));

        mockMvc.perform(get("/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Maria"));
    }

    @Test
    @DisplayName("POST /estudiantes - Debe registrar con éxito")
    void testRegistrarOk() throws Exception {
        EstudianteRequestDTO request = new EstudianteRequestDTO("Juan", "IngSoftware");
        Estudiante estudiante = new Estudiante(1L, "Juan", "IngSoftware");

        when(sistemaMock.registrarEstudiante(anyString(), anyString())).thenReturn(estudiante);

        mockMvc.perform(post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("GET /estudiantes/{id}/promedio - 404 cuando no existe")
    void testPromedioNotFound() throws Exception {
        when(sistemaMock.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/estudiantes/99/promedio"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /estudiantes/{id}/notas - Debe asignar nota correctamente")
    void testAsiganrNotaOk() throws Exception {
        NotaDTO nota = new NotaDTO(4.6);

        mockMvc.perform(post("/estudiantes/1/notas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nota)))
                .andExpect((status().isOk()))
                .andExpect(content().string("Se asigno 4.5"));

        verify(sistemaMock).asignarNota(1L, 4.6);
    }

    @Test
    @DisplayName("GET /estudiantes/{id}/promedio -  Debe retornar promedio")
    void testPromedioOk() throws Exception {
        Estudiante e = new Estudiante(1L, "Juan", "IngSoftware");
        e.agregarNota(4.1);

        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/estudiantes/1/promedio"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.1"));
    }

    @Test
    @DisplayName("GET /estudiantes/{id}/estado - Debe retornar APROBADO")
    void testEstadoAprobado() throws Exception {
        Estudiante e = mock(Estudiante.class);
        when(e.estaAprobado()).thenReturn(true);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/estudiantes/1/estado"))
                .andExpect(status().isOk())
                .andExpect(content().string("APROBADO"));
    }

    @Test
    @DisplayName("GET /estudiantes/{id}/estado - Debe retornar REPROBADO")
    void testEstadoReprobado() throws Exception {
        Estudiante e = mock(Estudiante.class);
        when(e.estaAprobado()).thenReturn(false);
        when(sistemaMock.buscarPorId(1L)).thenReturn(e);

        mockMvc.perform(get("/estudiantes/1/estado"))
                .andExpect(status().isOk())
                .andExpect(content().string("REPROBADO"));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el estudiante no existe")
    void testEstudianteNoEncontrado() throws Exception {
        when(sistemaMock.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/estudiantes/99/promedio"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/estudiantes/99/estado"))
                .andExpect(status().isNotFound());
    }
}

