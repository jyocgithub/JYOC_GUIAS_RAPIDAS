package jg_java.guia_30_criptografia.cifrado_cesar;

public class CifradoCesar {

    public static void main(String[] args) {

        String texto = "HOLA BUENAS TARDES COSITA";
        String cifrado = cifrar(texto, 3);

        System.out.println(cifrado);

    }

    public static String cifrar(String texto, int desplazamiento) {
        String res = "";
        for (int i = 0; i < texto.length(); i++) {

            int ascii = (int) texto.charAt(i);
            if (ascii != 32) {
                int asciidesplazado = ascii + desplazamiento;
                if (asciidesplazado > 90) {
                    asciidesplazado = 65 + (asciidesplazado%26);
                }
                res = res + Character.toString((char) asciidesplazado);
            }
        }
        return res;
    }
}
