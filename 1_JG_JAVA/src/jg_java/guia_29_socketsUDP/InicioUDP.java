package jyoc_java_guias.guia_29_socketsUDP;

public class InicioUDP {


    public static void main(String[] args) {

        Escuela s = new Escuela();
        s.alumnos.add(new Alumno("Pepe", 33));
        s.alumnos.add(new Alumno("JOse", 22));
        s.alumnos.add(new Alumno("Ana", 11));

        new ProcesarUDP().enviarObjetoUDP(s,"localhost",7000);


    }
}
