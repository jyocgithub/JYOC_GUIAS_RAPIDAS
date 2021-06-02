package jg_java.guia_29_socketsUDP;

public class ProcesoRecibeUDP {

    public static void main(String[] args) {

       Escuela s = (Escuela) new GestorSocketsUDP().recibirObjectUDP("localhost",7000);

       System.out.println(s);
    }
}
