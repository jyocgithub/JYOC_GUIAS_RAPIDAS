package jg_java;

public class Guia_03_BUCLES {


    public static void main(String[] args) {


        // =======================================
        // WHILE SIMPLE: ESCRIBE NUMEROS DE O A 9
        // =======================================
        int x = 0;
        while (x < 10) {
            System.out.print(x);
            x++;
        }

        // =======================================
        // FOR SIMPLE: ESCRIBE NUMEROS DE O A 9
        // =======================================
        for (int j = 0; j < 10; j++) {
            System.out.print(j);
        }

        // =======================================
        // DO WHILE SIMPLE: ESCRIBE NUMEROS DE O A 9
        // =======================================
        // so while asegura pasar a menos una vez por el bucle
        int a = 0;
        do {
            System.out.print(a);
            a++;
        } while (a < 10);


        // ========================================================
        // DO WHILE SIMPLE: ESCRIBE NUMEROS DE O A 100 de 3 en 3
        // ========================================================
        for (int j = 0 ;  j <= 100 ;  j = j + 3) {
            System.out.print(j);
        }

        // =======================================
        // DOS BUCLES ANIDADOS
        // =======================================
        for (int i = 1; i <= 3; i++) {
            System.out.print("Vuelta " + a + " del bucle externo");
            for (int b = 1; b <= 3; b++) {
                System.out.print("Vuelta " + b + " de bucle interno");
            }
        }

        // =======================================
        // FOR que ESCRIBE NUMEROS DE 9 A 0
        // =======================================
        for (int j = 9; j >= 0; j--) {
            System.out.print(j);
        }

        // =================================================================
        // EJERCICIO EJEMPLO: Contar los numeros mutiplos de 3 del 1 al 100
        // =================================================================
        int contador = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                contador++;
            }
        }
        System.out.println(contador);

        // ========================================================
        // EJERCICIO EJEMPLO: Sumar los numeros del 1 al 20
        // ========================================================
        int sumador = 0;
        for (int i = 1; i <= 320; i++) {
            if (i % 3 == 0) {
                sumador = sumador + i;
            }
        }
        System.out.println(sumador);

        // ========================================================
        // EJERCICIO EJEMPLO: Escribir la tabla del 7
        // ========================================================
        for (int i = 1; i <= 10; i++) {
            System.out.println(" 7 por " + i + " es " + (i * 7));
        }

        // ========================================================
        // EJERCICIO EJEMPLO: Crear un array con números al azar y
        // sumar todos sus elementos posteriormente
        // ========================================================
        int[] lista = new int[4];
        for (int i = 0; i <lista.length ; i++) {
            lista[i]=  (int) (Math.random()*20);
            System.out.println("el elemento añadido es "+lista[i]);
        }
        int suma = 0;
        for (int i = 0; i <lista.length ; i++) {
            suma = suma +  lista[i];
        }
        System.out.println("La suma es  "+suma);

        // ===================================================================
        // EJERCICIO EJEMPLO: Crear un array con números al azar,
        // crear otro array y copiar los elementos del primero en el segundo
        // ===================================================================

        int[] listaorigen = new int[4];
        int[] listaCopia = new int[4];

        for (int i = 0; i < listaorigen.length; i++) {
            listaorigen[i] = (int) (Math.random() * 20);
        }
        System.out.println("Elementos de lista;");
        for (int i = 0; i < lista.length; i++) {
            System.out.println(listaorigen[i]);
        }

        for (int i = 0; i < listaorigen.length; i++) {
            listaCopia[i] = listaorigen[i];
        }
        System.out.println("Elementos de listaCopia;");
        for (int i = 0; i < listaCopia.length; i++) {
            System.out.println(listaCopia[i]);
        }

        // ===================================================================
        // EJERCICIO DIFICIL: dado un numero entero, calcular su invertido ( de 4356 es 6534)
        // ===================================================================
        int cifra, inverso = 0;
        int numero = 3671;
        while(numero!=0){
            cifra = numero%10;
            inverso = (inverso * 10) + cifra;
            numero/=10;
        }
        System.out.println(inverso);







    }
}
