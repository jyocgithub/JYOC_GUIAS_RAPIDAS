package jg_java;

import java.util.Random;

public class Guia_04_ARRAYS {


    public static void main(String[] args) {

        // =======================================
        // ARRAYS
        // =======================================
        
        /*
         * CREAR UN ARRAY VACIO ---------------------------------------------------
         */
        int[] unArray = new int[4];

        /*
         * CREAR UN ARRAY CON CONTENIDO ---------------------------------------------------
         */
        String[] otroArray = {"Eva", "Ana", "Olga", "Luis"};

        /*
         * OBTENER EL TAMAÑO DE UN ARRAY ---------------------------------------------------
         */
        int tamano = unArray.length;

        /*
         * PATRON MODELO PARA RECORRER UN ARRAY ---------------------------------------------------
         */
        // En cada vuelta de bucle, aparece miArray[i]
        // que representará (en cada vuelta) cada uno de los elementos del array
        // ¿Que hacer con el? Lo que se pida, en cada caso

//        for (int i = 0; i < unArray.length; i++) {
//            unArray[i]
//        }



        /*
         * CREAR UN ARRAY AL TROCEAR UNA CADENA ---------------------------------------------------
         */
        String   cadena = "Scotty, mas potencia!";
        String[] trozos = cadena.split(" ");



        /*
         * EJERCICIO: Mostrar todos los elementos de un array
         */
        int[] miArray = {3,5,6,9};
        for (int i = 0; i < miArray.length; i++) {
            System.out.println(miArray[i]);
        }

        /*
         * EJERCICIO: Contar todos los elementos de un array
         */
        int contador = 0;
        for (int i = 0; i < unArray.length; i++) {
            if (unArray[i] % 2 == 0) {
                contador++;
            }
        }

        /*
         * EJERCICIO: Sumar todos los elementos de un array
         */
        int sumador = 0;
        for (int i = 0; i < unArray.length; i++) {
            sumador = sumador + unArray[i];
        }

        /*
         * EJERCICIO: Rellenar todos los elementos de un array con valores al azar
         */
        Random azar = new Random();
        for (int i = 0; i < unArray.length; i++) {
            unArray[i] = azar.nextInt(20);
        }

        /*
         * EJERCICIO: Ordenar  por burbuja  (version más simple), cuando el array contiene int
         */
        int[] datos = {4, 1, 8, 36, 0, 33, 11, 43};
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = 0; j < datos.length - 1; j++) {
                if (datos[j] > datos[j + 1]) {
                    int aux = datos[j + 1];
                    datos[j + 1] = datos[j];
                    datos[j] = aux;
                }
            }
        }

        /*
         * EJERCICIO: Ordenar por burbuja  (version más simple), cuando el array contiene String
         * (o de cualquier objeto)
         */
        String[] palabras = {"pera", "uva", "higo", "fresa"};
        for (int i = 0; i < palabras.length - 1; i++) {
            for (int j = 0; j < palabras.length - 1; j++) {
                if (palabras[j].compareTo(palabras[j + 1]) > 0) {
                    String aux = palabras[j + 1];
                    palabras[j + 1] = palabras[j];
                    palabras[j] = aux;
                }
            }
        }

        /*
         * EJERCICIO: Ordenar por burbuja  (version algo mas ajustada)
         */
        int[] datos2 = {4, 1, 8, 36, 0, 33, 11, 43};
        boolean heCambiado = true;
        for (int i = 0; i < datos2.length - 1 && heCambiado; i++) {
            for (int j = i; j < datos2.length - 1; j++) {
                heCambiado = false;
                if (datos2[j] > datos2[j + 1]) {
                    int aux = datos2[j + 1];
                    datos2[j + 1] = datos2[j];
                    datos2[j] = aux;
                    heCambiado=true;
                }
            }
        }

        

        // =======================================
        // MATRICES
        // =======================================

        /*
         * CREAR UNA MATRIZ VACIA
         */
        int[][] matrizvacia = new int[3][4]; //crea matriz 3x4 posiciones int

        /*
         * CREAR UNA MATRIZ CON CONTENIDO
         */
        int[][] matrizconchicha = {{4, 1, 8, 36}, {6,4,21,8}};


        /*
         * PATRON MODELO PARA RECORRER UNA MATRIZ
         */
        // En cada vuelta de matriz, aparece matriz[f][c]
        // que representará cada uno de los elementos de la matriz
        // ¿Que hacer con el? Lo que se pida, en cada caso

//        int[][] matriz = {{4, 1, 8, 36}, {4, 1, 8, 36}};
//        for (int f = 0; f < matriz.length; f++) {
//            for (int c = 0; c < matriz[0].length; c++) {
//                matriz[f][c]
//            }
//        }


        /*
         * EJERCICIO: Mostrar todos los elementos de una matriz
         */
        int[][] matriz1 = {{4, 1, 8, 36}, {6,4,21,8}};
        for (int f = 0; f < matriz1.length; f++) {
            for (int c = 0; c < matriz1[0].length; c++) {
                System.out.print(matriz1[f][c]);
                System.out.print("\t");
            }
            System.out.println("");  
        }

        /*
         * EJERCICIO: Sumar cada una de las filas de una matriz
         */
        int[][] matriz2 = {{4, 1, 8, 36}, {6,4,21,8}};
        int suma = 0;
        for (int f = 0; f < matriz2.length; f++) {
            for (int c = 0; c < matriz2[0].length; c++) {
                suma = suma + matriz2[f][c];
            }
            System.out.println("la suma de la fila " + f + " es " + suma);
            suma = 0; // SUPERIMPORTANTE volver a poner el sumador a 0 antes de recorrer una nueva fila
        }



        /*
         * EJERCICIO: Ordenar todos los elementos de una matriz por metodo de burbuja
         */
        int[][]  mat = {{14,7,23,45},{33,2,11,4},{8,1,99,45}};
        int n = mat.length;
        int m = mat[0].length;
        int t;

        for( int i=0; i < n; i++){
            for( int j=0;j< m; j++){
                for(int x=0; x < n; x++){
                    for(int y=0; y <m; y++){
                        if(mat[i][j] < mat[x][y]){    // ordena la matriz de menor a mayor
                            t = mat[i][j];
                            mat[i][j] = mat[x][y];
                            mat[x][y] = t;
                        }
                    }
                }
            }
        }
        // pintamos la matriz para ver si se ha ordenado
        for( int i=0; i < n; i++){// pinta la matriz
            for( int j=0;j< m; j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }


    }
}
