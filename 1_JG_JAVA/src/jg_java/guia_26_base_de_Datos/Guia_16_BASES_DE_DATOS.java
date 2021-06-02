package jg_java.guia_26_base_de_Datos;



import java.util.Date;

public class Guia_16_BASES_DE_DATOS {
    public static void main(String[] args) {

        // Todos los metodos estan en GestorBBDD
        // Pruebas de la clase:
        GestorBBDD bd = new GestorBBDD();
        // probamos solo de entrada si se puede conectar , para ver si hay bbdd activa
        bd.conectar();
    }
}

