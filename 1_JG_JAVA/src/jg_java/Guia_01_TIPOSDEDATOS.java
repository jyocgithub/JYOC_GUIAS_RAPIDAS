package jyoc_java_guias;

import java.util.Random;
import java.util.Scanner;

public class Guia_01_TIPOSDEDATOS {


    public static void main(String[] args) {


        // =======================================
        // CONVERSIONES    INT - STRING
        // =======================================ç

        /*
         * ----------  CONVERTIR un STRING en un INT
         */
        String ss = "53";
        int num1 = Integer.parseInt( ss );
        int num2 = Integer.parseInt( "323");   // sin usar una variable previamente

        // existen Double.parseDouble(), Short.parseShort(), etc...

        /*
         * ----------  CONVERTIR un INT en un STRING
         */

        // este es el modo "didacticamente apropiado"
        int num = 2347;
        String cade1 = String.valueOf (num);

        // y este es el truqui que todos los mortales usamos
        String cade2 = num+"";



        // ================================================
        // CASTING - CONVERSION ENTRE TIPOS NUMERICOS
        // ================================================
        int i = 23;
        double d = 34.89;

        d = i;  // se hace un casting implicitio, directamente por el traductor
        i  = (int) d;  // se necesita hacer un casting explicitio. i vale ahora 34 (no redondea)



        // =======================================
        // SALIDA POR CONSOLA
        // =======================================

        System.out.println("Mensaje que acaba en un salto de linea ");
        System.out.print("Mensaje que no caba en un salto de linea ");
        System.out.print("Mensaje que si acaba en un salto de linea \n");


        // =======================================
        // ENTRADA POR TECLADO
        // =======================================

        Scanner entrada = new Scanner(System.in);
        String linea = entrada.nextLine();
        int numentero = entrada.nextInt();
        double numdouble = entrada.nextDouble();
        // Se recomienda usar un Scanner distinto para numeros y para Strings


        // =======================================
        // RANDOM  _ ELEGIR NUMEROS AL AZAR
        // =======================================

        /*
         * ----------  CON MATH.random()
         */

        // ALEATORIOS ENTEROS
        // ===================================================================
        int MAX = 100;
        int MIN  = 20;
        //--------- Aleatorio entre 0 y max, sin incluir MAX
        int i1 = (int) (Math.random()*(MAX));

        //--------- Aleatorio entre 0 y max, incluyendo max
        int i2 = (int) (Math.random()*(MAX+1));

        //--------- Aleatorio entre MIN y MAX, sin incluir MAX
        int s1 = (int) ((Math.random()*(MAX-MIN))+MIN);

        //--------- Aleatorio entre MIN y MAX, incluyendo MAX
        int s2 = (int) ((Math.random()*(MAX-MIN+1))+MIN);


        /*
         * ----------  CON RANDOM
         */

        Random azar = new Random();
        int va1 = azar.nextInt(10);             // de 0 a 9
        int va2 = azar.nextInt(100-50+1) + 50;  // de 50 a 100

        /*
         * MAS ABAJO EXISTE UN MÉTODO DIRECTO PARA OBTENER UN NUMERO AL AZAR ENTRE DOS LIMITES
         * ESTE ES SU USO;
         */
        int numeroalazart = enteroAlAzar(10,20);  // devuelve un numero entre 10 y 20 ambos inclusive





        // =======================================
        // EJEMPLO : pide una edad y muestra un mensaje con dicha edad
        // =======================================

        // Se crea una variable llamada (por ejemplo) input, que es como una maquina de leer cosas por la consola
        Scanner input = new Scanner(System.in);
        // Ponemos un mensaje al usuario para que vea lo que tiene que escribir
        System.out.println("dame tu edad ");
        // Llamamos a la "maquina de leer" (input) y le pedimos que lea un int (nextInt() )
        // El valor que escribimos en el teclado va a parar a la variable 'edad'
        int edad = input.nextInt();
        // Escribimos un mensaje con la edad que acabamos de escribir
        System.out.println("anda pues tienes " + edad + " años");
        // al acabar el programa, cerramos (usando close() ) la maquina de leer por teclado
        input.close();


        // =======================================
        // EJEMPLO :  pide dos números y muestra su suma
        // =======================================
        // Se crea una variable llamada input2, que es como una maquina de leer cosas por la consola
        Scanner input2 = new Scanner(System.in);
        // Ponemos un mensaje al usuario para que vea lo que tiene que escribir
        System.out.println("dame un primer numero");
        // Llamamos a la "maquina de leer" (input) y le pedimos que lea un int (nextInt() )
        // El valor que escribimos en el teclado va a parar a la variable 'valor1'
        int valor1 = input2.nextInt();
        // Ponemos un mensaje al usuario para que vea lo que tiene que escribir
        System.out.println("dame un segundo numero");
        // Llamamos a la "maquina de leer" (input) y le pedimos que lea un int (nextInt() )
        // El valor que escribimos en el teclado va a parar a la variable 'valor2'
        int valor2 = input2.nextInt();
        // Una vez que tenemos los dos valores , los sumamos y guardamos en otra variable (resultado)
        int resultado = valor1 + valor2;
        // Escribimos un mensaje con el resultado de la suma (que es la varible resultado)
        System.out.println("El resultado de la suma es " + resultado + " y ya hemos acabado");
        // al acabar el programa, cerramos (usando close() ) la maquina de leer por teclado
        input2.close();



        // =======================================
        // EJEMPLO : convertir kilograms en pounds
        // =======================================

        // Se crea una variable llamada input3, que es como una maquina de leer cosas por la consola
        Scanner input3 = new Scanner(System.in);

        // Se crea una CONSTANTE llamada POUNDS_IN_KILOGRAM que indica cuantos pounds hay en un kilogram
        final double POUNDS_POR_KILO = 2.204620;

        // Ponemos un mensaje al usuario para que vea lo que tiene que escribir
        System.out.println("Dime una cantidad de kilos :");

        // Llamamos a la "maquina de leer" (input) y le pedimos que lea un double (nextDouble() )
        // El valor que escribimos en el teclado va a parar a la variable 'numberOfKg'
        double numberOfKg = input3.nextDouble();

        // Calculamos cuantas pounds hay en los kilos pedidos antes
        // y el resulado lo guardamos en la variable numberOfPounds
        double numberOfPounds = numberOfKg * POUNDS_POR_KILO;

        // Escribimos un mensaje con el resultado de la conversion (que es la varible numberOfPounds)
        // Y añadimos algo de texto, para dar mas informacion.
        System.out.println(numberOfKg + " kg son " + numberOfPounds + " pounds");

        // al acabar el programa, cerramos (usando close() ) la maquina de leer por teclado
        input.close();



    }

    public static int enteroAlAzar(int minimo, int maximo) {
        return (int)(Math.random() * (maximo - minimo + 1)  + minimo);
    }


}
