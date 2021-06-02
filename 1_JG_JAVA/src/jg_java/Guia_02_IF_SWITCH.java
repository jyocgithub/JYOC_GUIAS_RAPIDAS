package jg_java;

public class Guia_02_IF_SWITCH {


    public static void main(String[] args) {

        int edad = 20;

        /*
         * IF    (SIMPLE)
         */

        if (edad > 17) {
            System.out.print("Es mayor de edad");
        }


        /*
         * IF - ELSE
         */
        if (edad > 18) {
            System.out.println("Es mayor de edad");
        } else {
            System.out.println("No es mayor de edad");
        }


        /*
         * IF - ELSE IF
         */
        if (edad > 65) {
            System.out.println("Esta jubilado");
        } else if (edad > 17) {
            System.out.println("Ha acabado el colegio");
        } else if (edad > 5) {
            System.out.println("Esta en primaria");
        } else {
            System.out.println("No ha empezado a ir al cole");
        }

        /*
         * IF anidado (uno dentro de otro)
         */
        if (edad > 15) {
            if (edad > 17) {
                System.out.println("Puede ir a la universidad");
            } else {
                System.out.println("Puede hacer bachillerato");
            }
        }else{
            System.out.println("Aun debe ir al colegio");
        }


        /*
         * SWITCH
         */
        int valor  = 2;
        switch (valor)  {
            case 0:                              //puede estar vacío
            case 1:
                System.out.print("ya es > 1");   // sin break, continua en los demas CASE
            case 2:
                System.out.print("< 3");
                break;
            case 3:
                System.out.print("= 3");
                break;
            default:                             // no es obligatorio
                System.out.print("> 3");
        }


        // =======================================
        // EJEMPLO : indica si un numero es par
        // =======================================
        int n = 34;
        if ( n % 2 == 0) {
            System.out.println("Es par");
        }

        // =======================================
        // EJEMPLO : indica si un numero es impar
        // =======================================
        int b = 34;
        if ( b % 2 != 0) {
            System.out.println("Es impar");
        }

        // =======================================
        // EJEMPLO : indica si un numero es multiplo de 7
        // =======================================

        // si un numero es multiplo de 7 ....
        int z = 34;
        if ( z % 7 == 0) {
            System.out.println("Es multiplo de 7");
        }

        // =======================================
        // EJEMPLO : ver si un año es bisiesto
        // =======================================
        int anio  = 2010;
        // Un año es bisiesto si es divisible entre 4 y no es divisible entre 100,
        // o bien es divisible entre 100 y 400 a la vez.
        if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 100 == 0 && anio % 400 == 0)) {
            System.out.println("El año " + anio + " es bisiesto");
        } else {
            System.out.println("El año " + anio + " no es bisiesto");
        }




    }
}
