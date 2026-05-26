package juanca.registroestudiantes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String programa;
    @ElementCollection
    @CollectionTable(name = "estudiante_notas", joinColumns = @JoinColumn(name = "estudiante_id"))
    @Column(name = "nota")
    private List<Double> notas = new ArrayList<>();

    public Estudiante(String nombre, String programa, Double nota) {
        validarDatosBasicos(nombre, programa);
        this.nombre = nombre;
        this.programa = programa;
        if (nota != null) {
            agregarNota(nota);
        }
    }

    public Estudiante(Long id, String nombre, String programa) {
        validarDatosBasicos(nombre, programa);
        this.id = id;
        this.nombre = nombre;
        this.programa = programa;
    }

    public Estudiante(String nombre, String programa) {
        validarDatosBasicos(nombre, programa);
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

    public static boolean validarRango(double nota){
        return nota>= 0.0 && nota<=5.0;
    }

    private static void validarDatosBasicos(String nombre, String programa) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre invalido");
        }
        if(programa == null || programa.isBlank()){
            throw new IllegalArgumentException("Programa invalido");
        }
    }
}
