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
}

