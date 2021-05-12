package jyoc_java_guias.guia_29_socketsUDP;

public class ReceptorUDP {


    public static void main(String[] args) {

       Escuela s = (Escuela) new ProcesarUDP().recibirObjectUDP("localhost",7000);

       System.out.println(s);
    }
}
