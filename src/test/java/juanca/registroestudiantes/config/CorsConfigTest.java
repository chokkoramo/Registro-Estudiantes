package juanca.registroestudiantes.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.Mockito.*;

class CorsConfigTest {

    @Test
    void testAddCorsMappings() {
        CorsRegistry registry = mock(CorsRegistry.class);
        CorsRegistration registration = mock(CorsRegistration.class);
        when(registry.addMapping("/api/**")).thenReturn(registration);
        when(registration.allowedOrigins("http://localhost:3001", "http://localhost:5173", "http://frontend:3001"))
                .thenReturn(registration);
        when(registration.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"))
                .thenReturn(registration);
        when(registration.allowedHeaders("*")).thenReturn(registration);

        new CorsConfig().addCorsMappings(registry);

        verify(registry).addMapping("/api/**");
        verify(registration).allowedOrigins("http://localhost:3001", "http://localhost:5173",  "http://frontend:3001");
        verify(registration).allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
        verify(registration).allowedHeaders("*");
    }
}
