package jg_java.guia_29_socketsUDP;

import java.io.Serializable;

public
class Alumno implements Serializable {
    String nombre;
    int edad;

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;


        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
