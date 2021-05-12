package jyoc_java_metodos_utilidades;

import java.util.*;

/*
#######################################################################################
############################  GUIA DE CONTENIDOS ######################################
#######################################################################################
#                                                                                     #
# SCANNER SIMPLIFICADO                                                                #
# ####################                                                                #
#     - leerInt     (String mensaje)                                                  #
#     - leerDouble  (String mensaje)                                                  #
#     - leerString  (String mensaje)                                                  #
#                                                                                     #
###################################### (fin) ##########################################
 */

/**
 * JYOCUtilsJavaScanner
 * <p>
 * Utilidades para despedirse de usar siempre Scanner con la consola de un IDE
 *
 * @author Iñaki Martin
 * @version 2
 */
public class JYOCJAVAUtils_Scanner {

    /**
     * leerInt Lee con scanner un numero int, precio envio de un mensaje a usuario
     *
     * @param mensaje se muestra al usuario antes de que este escribe el valor
     *                pedido
     * @return el valor int que el usuario introduce por teclado
     */
    public static int leerInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(mensaje);
                int numero = sc.nextInt();
                return numero;
            } catch (NumberFormatException ex) {
                System.out.println("Debe indicar un valor numerico entero");
            }
        }
    }

    /**
     * leerDouble Lee con scanner un numero double, precio envio de un mensaje a
     * usuario
     *
     * @param mensaje se muestra al usuario antes de que este escribe el valor
     *                pedido
     * @return el valor double que el usuario introduce por teclado
     */
    public static double leerDouble(String mensaje) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(mensaje);
                double numero = sc.nextDouble();
                return numero;
            } catch (NumberFormatException ex) {
                System.out.println("Debe indicar un valor numerico double");
            }
        }
    }

    /**
     * leerString Lee con scanner un string, precio envio de un mensaje a usuario
     *
     * @param mensaje se muestra al usuario antes de que este escribe el valor
     *                pedido
     * @return el valor String que el usuario introduce por teclado
     */
    public static String leerString(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        String res = sc.nextLine();
        return res;
    }




}
