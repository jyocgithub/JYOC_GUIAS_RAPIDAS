package jyoc_java_metodos_utilidades;

import java.io.*;
import java.util.Random;
import java.util.regex.Pattern;

/*

#######################################################################################
############################  GUIA DE CONTENIDOS ######################################
#######################################################################################
#                                                                                     #
# STRING (JAVA)                                                                       #
# #############                                                                       #
#     - indiceDeEnesimaOcurrencia  (String origen, String busqueda, int ocurrencia    #
#     - validarRegEx               ( String expresionregular, String valor)           #
#     - cuantasVeces               (String origen, char busqueda)                     #
#     - generarClaveAleatoria      (int tamano)                                       #
#     - posicionEnArray            (String str, String[] donde)                       #
#                                                                                     #
###################################### (fin) ##########################################

 */

public class JYOCJAVAUtils_String {


    /**
     * ***************************************************************************
     * ************ STRING (JAVA) **************
     * ***************************************************************************
     */


    /**
     * indiceDeEnesimaOcurrencia
     * <p>
     * Devuelve la posision de una cadena en otra, en su enésima aparición
     *
     * @param origen     Cadena donde buscar
     * @param busqueda   Cadena que se busca
     * @param ocurrencia numero de ocurrencia buscada
     * @return la posision de la enésima aparición de la cadena buscada. o -1 si
     * algun parametro es incorrecto
     */
    public static int indiceDeEnesimaOcurrencia(String origen, String busqueda, int ocurrencia) {
        int pos = -1;
        if (origen == null || busqueda == null || ocurrencia < 1) {
            return -1;
        }
        do {
            pos = origen.indexOf(busqueda, pos + 1);
        } while (ocurrencia-- > 0 && pos != -1);

        return pos;
    }
    /**
     * cuantas veces
     * <p>
     * Cuenta cuantas veces aparece un char en un String
     *
     * @param origen     Cadena donde buscar
     * @param busqueda   char buscado
     * @return numero de apariciones de char en la cadena o -1 si la cadena pasada es null
     */
    public static int cuantasVeces(String origen, char busqueda) {
        int cont  = 0;
        if (origen == null ) {
            return -1;
        }

        for (int i = 0; i <origen.length() ; i++) {
            if(origen.charAt(i)== busqueda){
                cont++;
            }
        }
        // version en una sola instruccion
        //cont = origen.length() - origen.replace(busqueda+"", "").length();
        return cont;
    }

    /**
     * validarRegEx
     * Valida una xexpresion regular usando Pattern, pero sin compilar la expresion
     *
     * @param expresionregular expresion regular que comprueba el valor
     * @param valor     valor que se  quiere comprobar
     * @return true si el valor cumple la expresion regular dada
     */
    public static boolean validarRegEx(String expresionregular, String valor) {
        // return valor.matches(expresionregular);  // otra forma
        return Pattern.matches(expresionregular, valor);
    }

    /**
     * generarClaveAleatoria
     * Crea una cadena de texto aleatoria con letras mayusculas minusculas y numeros, de un tamaño definido
     *
     * @param tamano tamaño de la cadena a crear
     * @return una cadena aleatoria
     */
    public static String generarClaveAleatoria(int tamano) {
        String madre ="ABCDEFGHIJKLMNOPQRSTUVWXUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random azar = new Random();
        String resultado = "";
        for (int i = 0; i < tamano ; i++) {
            resultado += madre.charAt( azar.nextInt(madre.length()));
        }
        return resultado;
    }


    /**
     * posicionEnArray
     *
     * Busca una cadena en un array de Strings
     * Devuelve posicion de la cadena, o -1 si no esta
     * @param str  cadena a buscar
     * @param donde array de string donde buscar
     * @return  posicion de la cadena, o -1 si no esta
     */
    public static int posicionEnArray(String str, String[] donde) {
        int resultado = -1;
        for (int i = 0; i < donde.length && resultado == -1; i++) {
            if (donde[i].equals(str))
                resultado = i;
        }
        return resultado;
    }




    // por si alguien quiere probar los metodos previos .......
    public static void main(String[] args) throws IOException {

        System.out.println(generarClaveAleatoria(15));

    }





}
