package jyoc_java_guias;

import java.io.*;
import java.util.ArrayList;

public class Guia_14_FICHEROS {


    public static void main(String[] args) {

    }

    // ***********************************************************************************************
    // ***********************************************************************************************
    // ******                                                                                   ******
    // ******                                 FICHEROS DE TEXTO (JAVA)                          ******
    // ******                                                                                   ******
    // ***********************************************************************************************
    // ***********************************************************************************************


    //  +-------------------------------------------------+
    //  |   FICHERO TEXTO                                 |
    //  |   ESCRIBIR        DESDE EL PRINCIPIO DE FICHERO |
    //  +-------------------------------------------------+

    public static void escribirFicheroTexto() {
        PrintWriter pw = null;


        try {

            /**  INICIO de donde se escribe lo que se desee, con los métodos
             *       println( cualquiercosa )
             *       print( cualquiercosa )
             */
            pw = new PrintWriter("textos.txt");
            pw.println("hola jaja estamos a " + 5 + " grados");
            pw.println("fin de fichefo aqui");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            pw.close();
        }
    }

    //  +-------------------------------------------------+
    //  |   FICHERO TEXTO                                 |
    //  |   ESCRIBIR           AÑADIENDO  AL FINAL        |
    //  +-------------------------------------------------+

    public static void anadirFicheroTexto() {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter("textos.txt", true));


            /**  INICIO de donde se escribe lo que se desee, con los métodos
             *       write( cualquiercosa )
             *       newline()                // para meter un salto de linea
             */
            bw.write("probando escritura\n");
            bw.newLine();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    //  +-------------------------------------------------+
    //  |   FICHERO TEXTO                                 |
    //  |   LEER                  DESDE INICIO            |
    //  +-------------------------------------------------+

    public static void leerFicheroTexto() {
        File archivo = new File("textos.txt");

        if (archivo.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(archivo));

                String linea = br.readLine();
                while (linea != null) {

                    /**  INICIO de zona de lectura, se lee linea a linea siempre
                     *   y aqui se hace lo que se desee con cada linea leida
                     */
                    System.out.println(linea);

                    linea = br.readLine();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // ***********************************************************************************************
    // ***********************************************************************************************
    // ******                                                                                   ******
    // ******                           FICHEROS BINARIOS (SIN OBJETOS)                         ******
    // ******                                                                                   ******
    // ***********************************************************************************************
    // ***********************************************************************************************


    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO                               |
    //  |   ESCRIBIR         DESDE INICIO                 |
    //  +-------------------------------------------------+

    public void escribirFicheroBinario() {
        String nombreFichero = "archivo.dat";
        DataOutputStream escribir = null;

        try {
            // true a continuación del nombreFichero indica que se añaden datos al archivo
            // sin true se añadirán datos desde cero cada vez que se ejecute el método
            escribir = new DataOutputStream(new FileOutputStream(nombreFichero));

            /**  INICIO de donde se escribe lo que se desee, con los métodos
             *       writeUTF(aquivaunString)
             *       writeInt(aquivaunInt
             *       writeDouble(aquivaunDpouble)
             *       ... etc
             */
            // Podemos escribir los valores uno a uno ...
            String unString = "cadena guardada";
            double unNumero = 55.3;

            escribir.writeUTF(unString);
            escribir.writeDouble(unNumero);

            // ... o podemos escribir las cosas recorriendo una coleccion con un bucle y escribiendo sus valores
            int[] numeros = {4, 5, 2, 5, 3, 5, 3, 5, 3};
            for (int i = 0; i < numeros.length; i++) {
                escribir.writeInt(numeros[i]);
            }

            /**  FIN de donde se escribe    */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (escribir != null) {
                    escribir.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO                               |
    //  |   ESCRIBIR        AÑADIENDO AL FINAL            |
    //  +-------------------------------------------------+
    public void anadirFicheroBinario() {
        String nombreFichero = "archivo.dat";
        DataOutputStream escribir = null;

        try {
            //true a continuación del nombreFichero indica que se añaden datos al archivo
            //sin true se añadirán datos desde cero cada vez que se ejecute el método
            escribir = new DataOutputStream(new FileOutputStream(nombreFichero, true));


            /**  INICIO de donde se escribe lo que se desee, con los métodos
             *       writeUTF(aquivaunString)
             *       writeInt(aquivaunInt
             *       writeDouble(aquivaunDpouble)
             *       ... etc
             */
            // Podemos escribir los valores uno a uno ...
            String unString = "cadena guardada";
            double unNumero = 55.3;

            escribir.writeUTF(unString);
            escribir.writeDouble(unNumero);

            // ... o podemos escribir las cosas recorriendo una coleccion con un bucle y escribiendo sus valores
            int[] numeros = {4, 5, 2, 5, 3, 5, 3, 5, 3};
            for (int i = 0; i < numeros.length; i++) {
                escribir.writeInt(numeros[i]);
            }

            /**  FIN de donde se escribe    */


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (escribir != null) {
                    escribir.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO                               |
    //  |   LEER                      DESDE INICIO        |
    //  +-------------------------------------------------+
    public void leerFicheroBinario() {
        String nombreFichero = "archivo.dat";
        DataInputStream leer = null;

        try {
            leer = new DataInputStream(new FileInputStream(nombreFichero));


            /**  INICIO de donde se lee, se lee con metodos
             *       readUTF()     que lee y devuelve un String
             *       readInt)      que lee y devuelve un int
             *       readDouble()  que lee y devuelve un double
             */

            // Podemos leer los valores uno a uno ... si sabemos que es lo que hay
            String algo = leer.readUTF();
            double num = leer.readDouble();
            System.out.println("Lectura binario: " + algo + num);

            // ... o podemos leer muchas cosas sin parar y sin preocuparnos de que haya error al llegar al final
            // puses pondremos un catch que controle esos problemas
            while (true) {
                int i = leer.readInt();
            }

            /**  FIN de donde se lee    */


        } catch (EOFException e) {
            System.out.println("Fin de fichero");    //catch alcanzado al acabar de leer el fichero
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // ***********************************************************************************************
    // ***********************************************************************************************
    // ******                                                                                   ******
    // ******                           FICHEROS BINARIOS (CON OBJETOS)                         ******
    // ******                                                                                   ******
    // ***********************************************************************************************
    // ***********************************************************************************************


    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO DE OBJETOS                    |
    //  |   ESCRIBIR       DESDE INICIO                   |
    //  +-------------------------------------------------+

    public void escribirFicheroBinarioObjetos() {
        String nombreFichero = "archivo.dat";
        ObjectOutputStream escribir = null;

        try {
            //true a continuación del nombreFichero indica que se añaden datos al archivo
            //sin true se añadirán datos desde cero cada vez que se ejecute el método
            escribir = new ObjectOutputStream(new FileOutputStream(nombreFichero, true));

            /**  INICIO de donde se escribe lo que se desee, con los métodos de antes MAS
             *       writeObject( unobjeto )
             */
            ArrayList<Tanque> listatanques = new ArrayList<>();
            // se supone que el arraylist anterior esta ya lleno de objetos Tanque

            // podemos escribir los objetos recorriendo una coleccion con un bucle y escribiendo sus valores
            for (int i = 0; i < 5; i++) {
                escribir.writeObject(listatanques.get(i));
            }
            /**  FIN de donde se escribe    */


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (escribir != null) {
                    escribir.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO DE OBJETOS                    |
    //  |   ESCRIBIR       AÑADIENDO AL FINAL             |
    //  |    !! NO SE PUEDE HACER DE MODO BASICO  !!      |
    //  +-------------------------------------------------+


    //  +-------------------------------------------------+
    //  |   FICHERO BINARIO DE OBJETOS                    |
    //  |   LEER           DESDE INICIO                   |
    //  +-------------------------------------------------+
    public void leerFicheroBinarioObjetos() {
        String nombreFichero = "archivo.dat";
        ObjectInputStream leer = null;

        try {
            leer = new ObjectInputStream(new FileInputStream(nombreFichero));

            /**  INICIO de donde se lee, se lee con metodos
             *       readUTF()     que lee y devuelve un String
             *       readInt)      que lee y devuelve un int
             *       readDouble()  que lee y devuelve un double
             *       readObject()  que lee un OBJETO
             */

            // creamos una lista nueva para rellenarla con lo que se lea
            ArrayList<Tanque> listatanques = new ArrayList<>();

            // Podemos leer muchas cosas sin parar y sin preocuparnos de que haya error al llegar al final
            // pues pondremos un catch que controle esos problemas
            while (true) {
                Tanque av = (Tanque) leer.readObject();  // CUIDADO QUE HACE FALTA UN CAST
                listatanques.add(av);
            }
            /**  FIN de donde se lee    */


        } catch (EOFException e) {
            System.out.println("Fin de fichero");     //catch alcanzado al acabar de leer el fichero
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {       // CUIDADI SE NECESITA ESTE CATCH arrojado por la lectura del obejto
            ex.printStackTrace();
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }




    // ***********************************************************************************************
    // ***********************************************************************************************
    // ******                                                                                   ******
    // ******                           FICHEROS ALEATRIOS (CON Y SIN OBJETOS)                  ******
    // ******                                                                                   ******
    // ***********************************************************************************************
    // ***********************************************************************************************


    //  +-------------------------------------------------+
    //  |   FICHERO ALEATORIO                             |
    //  |   ESCRIBIR COLECCION DE CADENAS SIMPLES         |
    //  +-------------------------------------------------+
    public void escribirFicheroAleatorioListaCadenas() {


        // ESCRIBIR
        // Haremos que cada cadena tenga 12 chars (TAM_NOMBRE),
        // Escribiremos con WriteUTF, donde cada cadena ocupa de longitud 1 byte por char, más 2 de regalo al inicio
        // El tamaño en fichero de cada registro sera por tanto de 14 bytes (TAM_REGISTRO)
        final int TAM_NOMBRE = 12;
        final int TAM_REGISTRO = 14;
        // Creamos primero la lista de cadenas
        ArrayList<String> lista = new ArrayList<>();
        lista.add("ANA");
        lista.add("MARINA");
        lista.add("VERONICA");
        lista.add("EVA");

        // escribimos
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("PRURAF.dat", "rw");

            for (String nombre : lista) {
                String ampliado = rellenarConEspacios(nombre, TAM_NOMBRE);
                raf.writeUTF(ampliado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //  +-------------------------------------------------+
    //  |   FICHERO ALEATORIO                             |
    //  |   LEER COLECCION DE CADENAS SIMPLES             |
    //  +-------------------------------------------------+
    public void leerFicheroAleatorioListaCadenas() {
        // Hicimos al escribir que cada cadena tuviera 12 chars (TAM_NOMBRE),
        // Como cada cadena ocupa de longitud 1 byte por char, más 2 de regalo al inicio,
        // el tamaño en fichero de cada registro sera por tanto de 14 bytes (TAM_REGISTRO)

        RandomAccessFile raf = null;
        ArrayList<String> listanueva = new ArrayList<>();
        final int TAM_NOMBRE = 12;
        final int TAM_REGISTRO = 14;

        // LEER. Lo hacemos con readUTF, y se hace directamente, pues el metodo lee la cadena completa sabiendo donde acaba

        //  --------- LEER TODOS LOS NOMBRES DESDE EL PRINCIPIO
        try {
            raf = new RandomAccessFile("PRURAF.dat", "rw");

            // Si quiero leer todos los nombres desde el principio,
            // me coloco en el byte de posicion 0
            raf.seek(0);
            while (true) {
                String nombre = raf.readUTF();
                nombre = nombre.trim();
                listanueva.add(nombre);
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero controlado");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  --------- LEER SOLO EL TERCER NOMBRE
        try {
            // ponerse donde empieza el tercer nombre, con la formula   : posicion = (numeroregistro-1) * tamañoderegistro
            //                                             en nuestro caso: posicion = ( 3  - 1 ) * 14
            int posicion = (3 - 1) * TAM_REGISTRO;
            raf.seek(posicion);
            String des = raf.readUTF();
            System.out.println(des);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //  +-------------------------------------------------------------------+
    //  |   FICHERO ALEATORIO                                               |
    //  |   ESCRIBIR COLECCION DE OBJETOS (tanques en este ejemplo)         |
    //  +-------------------------------------------------------------------+
    public void escribirFicheroAleatorioListaObjetos() {


        // ESCRIBIR
        // Haremos que cada nombre de tanque tenga 12 chars (TAM_NOMBRE),
        // El tamaño en fichero de cada OBJETO DE UN TANQUE sera  de 18 bytes (TAM_REGISTRO), puesto que :
        //     - el nombre ocupara 14 bytes, puesto que haremos que cada nombre de tanque tenga 12 chars (TAM_NOMBRE),
        //       (escribiremos con WriteUTF, donde cada cadena ocupa de longitud 1 byte por char, más 2 de regalo al inicio
        //        como el nombre es de 12 char, el tamaño de cada nombre sera de 14)
        //     - el nummotores es un int, que ocupan siempre 4 bytes. Se escribe con writeInt()
        final int TAM_NOMBRE = 12;
        final int TAM_REGISTRO = 18;
        // Creamos primero la lista de cadenas
        ArrayList<Tanque> listatanques = new ArrayList<>();

        listatanques.add(new Tanque("Panzer", 8));
        listatanques.add(new Tanque("Abrams", 16));
        listatanques.add(new Tanque("Leopard", 10));

        // escribimos
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("PRURAF.dat", "rw");

            for (Tanque cadatanque : listatanques) {
                String nombreampliado = rellenarConEspacios(cadatanque.getNombre(), TAM_NOMBRE);
                raf.writeUTF(nombreampliado);
                raf.writeInt(cadatanque.getNummotores());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //  +-------------------------------------------------------------------+
    //  |   FICHERO ALEATORIO                                               |
    //  |   LEER  COLECCION DE OBJETOS (tanques en este ejemplo)            |
    //  +-------------------------------------------------------------------+
    public void leerFicheroAleatorioListaObjetos() {
        // LEER
        // Hicimos al escribir que cada nombre de tanque tuviera 12 chars (TAM_NOMBRE),
        // El tamaño en fichero de cada OBJETO DE UN TANQUE sera de 18 bytes (TAM_REGISTRO), puesto que :
        //     - el nombre ocupa 14 bytes, puesto que hicimos que cada nombre de tanque tenga 12 chars (TAM_NOMBRE),
        //       (escribiremos con WriteUTF, donde cada cadena ocupa de longitud 1 byte por char, más 2 de regalo al inicio
        //        como el nombre es de 12 char, el tamaño de cada nombre sera de 14)
        //     - el nummotores es un int, que ocupan siempre 4 bytes. Se lee con readInt()
        RandomAccessFile raf = null;
        ArrayList<Tanque> listanuevatanques = new ArrayList<>();
        final int TAM_NOMBRE = 12;
        final int TAM_REGISTRO = 18;

        // LEER. Lo hacemos con readUTF, y se hace directamente, pues el metodo lee la cadena completa sabiendo donde acaba

        //  --------- LEER TODOS LOS NOMBRES DESDE EL PRINCIPIO
        try {
            raf = new RandomAccessFile("PRURAF.dat", "rw");

            // Si quiero leer todos los registros desde el principio,
            // me coloco en el byte de posicion 0
            raf.seek(0);
            Tanque cc;
            while (true) {                         // para cada tanque, leo el nombre y el nummotores
                String nombre = raf.readUTF();
                int nummot  = raf.readInt();
                cc = new Tanque(nombre, nummot);
                listanuevatanques.add(cc);
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero controlado");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //  --------- LEER SOLO EL TERCER TANQUE
        try {
            // ponerse donde empieza el tercer nombre, con la formula   : posicion = (numeroregistro-1) * tamañoderegistro
            //                                             en nuestro caso: posicion = ( 3  - 1 ) * 14
            int posicion = (3 - 1) * TAM_REGISTRO;
            raf.seek(posicion);
            String nombre = raf.readUTF();
            int nummot  = raf.readInt();
            Tanque t = new Tanque(nombre, nummot);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
























    // **********************************************
    // **********************************************
    // **                                          **
    // **     METODOS DE USO COMUN A TODOS LOS     **
    // **           TIPOS DE FICHEROS              **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |   RELLENAR UNA CADENA CON ESPACIOS              |
    //  +-------------------------------------------------+
    public static String rellenarConEspacios(String cadenaoriginal, int tamano) {
        // por simplicidad, no se controla que 'tamano' sea mayor
        // que el length de 'cadenaoriginal'     (aunque debería hacerse  :-)
        int cuantosBlancosDeboSumar = tamano - cadenaoriginal.length();
        for (int i = 1; i <= cuantosBlancosDeboSumar; i++) {
            cadenaoriginal += " ";
        }
        return cadenaoriginal;
    }

    public static String rellenarConEspaciosV2(String cadenaoriginal, int tamano) {
        if (cadenaoriginal.length() >= tamano) {
            return cadenaoriginal;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < tamano - cadenaoriginal.length()) {
            sb.append(' ');
        }
        sb.append(cadenaoriginal);

        return sb.toString();
    }

    //  +-------------------------------------------------+
    //  |   CREAR FICHERO  (DE TEXTO o BINARIO)  VACÍO    |
    //  +-------------------------------------------------+
    public static boolean crearFicheroVacio(String nombreFichero) { //nombre de fichero se pasa como parametro o
        File archivo = new File(nombreFichero);                     //se crea el fichero .dat en el mismo método

        try {
            if (archivo.exists()) {
                System.out.println("Ya existia el fichero. Borrelo si desea crearlo desde cero.");

            } else {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo " + nombreFichero + " Creado.");
                } else {
                    System.out.println("El archivo ya existe");
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("El archivo no se ha creado correctamente");
        }
        return false;
    }


}


//-----------------------------------------     Clases auxiliares para los ejemplos
class Tanque {

    private String nombre;
    private int nummotores;

    public Tanque(String nombre, int nummotores) {
        this.nombre = nombre;
        this.nummotores = nummotores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNummotores() {
        return nummotores;
    }

    public void setNummotores(int nummotores) {
        this.nummotores = nummotores;
    }

}


