package jyoc_java_guias;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Guia_23_EXPRESIONES_REGULARES {

    public static String rellenarConEspacios(String cadenaoriginal, int tamano) {
        // por simplicidad, no se controla que 'tamano' sea mayor
        // que el length de 'cadenaoriginal'     (aunque debería hacerse  :-)
        int cuantosBlancosDeboSumar = tamano-cadenaoriginal.length();
        for (int i = 1; i <= cuantosBlancosDeboSumar; i++) {
            cadenaoriginal += " ";
        }
        return cadenaoriginal;
    }


    public String rellenaConEspacios(String cadenaoriginal, int tamano) {
        if (cadenaoriginal.length() >= tamano) {
            return cadenaoriginal;
        }
        int cuantosBlancosDeboSumar = tamano-cadenaoriginal.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= cuantosBlancosDeboSumar; i++) {
            sb.append(' ');
        }
        sb.append(cadenaoriginal);
        return sb.toString();
    }


    public static void main(String[] args) {




        //  +-------------------------------------------------+
        //  |  EXPRESIONES REGULARES                          |
        //  +-------------------------------------------------+

        // ----------------  Comprobar si un String contiene “abc”
        String cadenabuena = "abc";
        String cadenamala = "abO";
        String patron = "abc.*";
        System.out.println(cadenabuena.matches(patron));   // TRUE
        System.out.println(cadenamala.matches(patron));   // FALSE

        // otra forma
        System.out.println(Pattern.matches(patron, "abc"));   // TRUE
        System.out.println(Pattern.matches(patron, "abX"));   // FALSE

        // ----------------  Comprobar si un String contiene “abc” o "Abc"
        System.out.println("abc".matches("[aA]bc.*"));   // TRUE
        System.out.println("aBc".matches("[aA]bc.*"));   // FALSE

        // ----------------  Comprobar si un String comienza por “abc” o "Abc"
        System.out.println("abc".matches("^[aA]bc.*"));   // TRUE
        System.out.println("aBc".matches("^[aA]bc.*"));   // FALSE

        // ----------------  Comprobar si un String contiene o bien “abc” o bien "Abc"
        System.out.println("zzabczz".matches("^.*[aA]bc.*$"));   // TRUE
        System.out.println("zzacczz".matches("^.*[aA]bc.*$"));   // TRUE

        // ----------------  Comprobar si un String está formado por un mínimo de 5 letras mayúsculas o minúsculas y un máximo de 10
        System.out.println("ffasd".matches("^[a-zA-Z]{5,10}$"));   // TRUE
        System.out.println("dfa44".matches("^[a-zA-Z]{5,10}$"));   // FALSE

        // ----------------  Comprobar si un String solo contiene los caracteres 0 ó 1, y debe contener algo
        System.out.println("01101001".matches("^(0|1)+$"));   // TRUE
        System.out.println("10020102".matches("^(0|1)+$"));   // FALSE

        // ----------------  Comprobar si un String es una matricula española (4 numeros y tres letras mayúsculas)
        System.out.println("3423GCB".matches("^[0-9]{4}[A-Z]{3}$"));   // TRUE
        System.out.println("342XGCB".matches("^[0-9]{4}[A-Z]{3}$" ));   // FALSE

        // ----------------   Comprobar si un String es un telefono movil (comienza por 6,7 u 8 , y tiene 9 numeros)
        System.out.println("676111222".matches("^[6-8][0-9]{8}$"));   // TRUE
        System.out.println("67611122".matches("^[6-8][0-9]{8}$"));    // FALSE

        // ----------------   Comprobar si un String contiene un 1 y ese 1 no está seguido por un 2
        System.out.println( "aa21ds".matches(".*1(?!2).*"));   // TRUE
        System.out.println("aa12ds".matches(".*1(?!2).*"));   // FALSE

        //  ----------------  Comprobar si un String  es una direccion de mail valida
        System.out.println("abc@mm.com".matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"));  // TRUE
        System.out.println("abcnmm.com".matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"));   // FALSE


        /*  Explicación paso a paso:
                [\\w-]+             El signo + indica que debe aparecer uno o más de los caracteres entre corchetes:
                                    \w indica "caracteres de la A a la Z tanto mayúsculas como minúsculas, dígitos del 0 al 9 y el carácter _"  y además el carácter –  "
                (\\.[\\w-]+)*	    El * indica que este grupo puede aparecer cero o más veces.
                                    El email puede contener de forma opcional un punto seguido de uno o más de los caracteres entre corchetes.
                @	                A continuación debe contener el carácter @
                [A-Za-z0-9]+	    Después de la @ el email debe contener uno o más de los caracteres que aparecen entre los corchetes
                (\\.[A-Za-z0-9]+)*	Seguido (opcional, 0 ó más veces) de un punto y 1 ó más de los caracteres entre corchetes
                (\\.[A-Za-z]{2,})	Seguido de un punto y al menos 2 de los caracteres que aparecen entre corchetes (final del email)

         */

        // ----------------   Comprobar si un String  contiene exactamente y exclusivamente la frase "Vita Brevis"
        //  "^Vita\\sBrevis$"  se puede escribir tambien como  "(\\W|^)Vita\\sBrevis(\\W|$)"
        System.out.println("Vita Brevis".matches("^Vita\\sBrevis$"));   // TRUE
        System.out.println("VitaBrevis".matches ("^Vita\\sBrevis$"));    // FALSE

         /*  Explicación paso a paso:
                (\\W|^)	            "Indica las dos opciones de inicio de la cadena, que son :
                                        \\W  indica que debe comenzar por algo que NO sea un "caracteres de la A a la Z tanto mayúsculas como minúsculas,
                                             dígitos del 0 al 9 y el carácter _" . Esto es, que vale si la comparación comienza por un retorno de línea,
                                             o un carácter especial

                                         ^    que es el inicio de una cadena normal, esto es, que no se permite nada antes de esto"
                (\\W|$)	            "Indica las dos opciones de fin de la cadena, que son :
                                        \\W  indica que debe terminar por algo que NO sea un "caracteres de la A a la Z tanto mayúsculas como minúsculas,
                                             dígitos del 0 al 9 y el carácter _" . Esto es, vale si la comparación termina en un retorno de línea,
                                             o fin de fichero, o un carácter especial
                                         $    es el fin de una cadena normal, esto es, que no se permite nada despues de esto"

         */


    }

}

