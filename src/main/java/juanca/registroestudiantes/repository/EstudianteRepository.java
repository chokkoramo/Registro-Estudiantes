package juanca.registroestudiantes.repository;

import juanca.registroestudiantes.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository  extends JpaRepository<Estudiante,Long> {
}
