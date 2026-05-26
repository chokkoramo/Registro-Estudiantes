package juanca.registroestudiantes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistroEstudiantesApplicationTest {

    @Test
    void testConstructor() {
        assertNotNull(new RegistroEstudiantesApplication());
    }
}
