package juanca.registroestudiantes.model;

import java.util.ArrayList;
import java.util.List;

public class SistemaAcademico {

    private List<Estudiante> estudiantes = new ArrayList<>();
    private Long contadorId = 1L;
    private String nombreSistema = "Sistema Academico";

    public Estudiante registrarEstudiante(String nombre, String programa){
        Estudiante e = new Estudiante(contadorId++, nombre, programa);
        estudiantes.add(e);
        return e;
    }

    public Estudiante buscarPor_Id(Long id){
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void asignar_Notas(Long id, double nota){
        if(id != null){
            if(nota >= 0){
                if(nota <= 5){
                    Estudiante e = buscarPor_Id(id);
                    if(e != null){
                        if(e.getPrograma() != null){
                            e.agregar_Notas(nota);
                        }
                    }
                }
            }
        }
    }

    public List<Estudiante> generarRanking(){

        estudiantes.sort((a, b) -> {

            double sumaA = 0;
            for(Double n : a.getNotas()){
                sumaA += n;
            }
            double promA = a.getNotas().isEmpty() ? 0 : sumaA / a.getNotas().size();

            double sumaB = 0;
            for(Double n : b.getNotas()){
                sumaB += n;
            }
            double promB = b.getNotas().isEmpty() ? 0 : sumaB / b.getNotas().size();

            return Double.compare(promB, promA);
        });

        return estudiantes;
    }

    public List<Estudiante> obtenerTodos(){
        return estudiantes;
    }
}
