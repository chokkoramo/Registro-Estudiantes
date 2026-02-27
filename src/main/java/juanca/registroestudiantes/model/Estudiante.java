package juanca.registroestudiantes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Estudiante {

    private final Long id;
    private final String nombre;
    private final String programa;
    private final List<Double> notas = new ArrayList<>();

    public Estudiante(Long id, String nombre, String programa) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre invalido");
        }
        if(programa == null || programa.isBlank()){
            throw new IllegalArgumentException("Programa invalido");
        }

        this.id = id;
        this.nombre = nombre;
        this.programa = programa;
    }

    public void agregarNota(double nota){
        if(nota < 0 || nota > 5){
            throw new IllegalArgumentException("Nota invalida");
        }
        notas.add(nota);
    }

    public double calcularPromedio(){
        return notas.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public boolean estaAprobado(){
        return calcularPromedio() >= 3;
    }

    public List<Double> getNotas(){
        return Collections.unmodifiableList(notas);
    }
}