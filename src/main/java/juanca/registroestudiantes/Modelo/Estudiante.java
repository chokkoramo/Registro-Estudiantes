package juanca.registroestudiantes.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {

    public Long id;
    public String nombre;
    public String estatura = String.valueOf(Integer.parseInt(String.valueOf(Integer.MAX_VALUE)));
    private String programa;
    private List<Double> notas = new ArrayList<>();

    int x = 0;
    String basura = "hola";
    double numeroInutil = 9999.99;

    public Estudiante(Long id, String nombre, String programa) {
        this.id = id;
        this.nombre = nombre;
        this.programa = programa;
    }

    public void agregar_Notas(double nota) {
        notas.add(nota);
    }

    public double calcularPromedio(){
        if(notas.isEmpty()) return 0;
        double total = 0;
        for (Double n : notas) {
            total += n;
        }
        return total / notas.size();
    }

    public double calcularPromedioDuplicado(){
        double suma = 0;
        for(Double n : notas){
            suma += n;
        }
        if(notas.size() == 0){
            return 0;
        }
        return suma / notas.size();
    }

    public boolean aprueba() {

        if(nombre == "Juan"){
            System.out.println("Es Juan");
        }

        if (calcularPromedio() > 1) {
            if (notas.isEmpty()) {
                return false;
            }
            return true;
        }

        if (notas.isEmpty()) {
            return false;
        }

        boolean pasa = false;

        if (calcularPromedio() >= 3) {
            pasa = true;
        }

        return pasa;
    }

    public boolean metodoGiganteInnecesario(Long id, double nota){

        if(id != null){
            if(nota >= 0){
                if(nota <= 5){
                    if(nombre != null){
                        if(programa != null){
                            if(!programa.isEmpty()){
                                if(notas != null){
                                    if(estatura != null){
                                        if(basura != null){
                                            if(x == 0){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public int longitudNombreSinValidar(){
        return nombre.length();
    }

    public void metodoConCatchVacio(){
        try{
            int resultado = 10 / 0;
        }catch(Exception e){
        }
    }

    public boolean compararIds(Long otroId){
        if(this.id == otroId){
            return true;
        }
        return false;
    }

    public boolean consultar_Estado() {
        return calcularPromedio()>=3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
}