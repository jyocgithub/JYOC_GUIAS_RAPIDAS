package jyoc_java_metodos_utilidades;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


/*

########################################################################################################
############################  GUIA DE CONTENIDOS #######################################################
########################################################################################################
#                                                                                                      #
# FICHEROS  (JAVA)                                                                                     #
# ################                                                                                     #
#    - escribirTextoEnFichero  (String texto, String nombrefichero, String pathdelfichero)             #
#    - leerTextoDeFichero (String nombrefichero, String pathdelfichero)                                #
#    - leerLineasDeFichero  (String nombrefichero, String pathdelfichero)                              #
#    - escribirBytearrayEnFichero (byte[] contenido, String nombrefichero, String pathdelfichero)      #
#    - leerBytearrayDeFichero (String nombrefichero, String pathdelfichero)                            #
#    - hermanosQueSonCarpetas  (File origen)                                                           #
#                                                                                                      #
###################################### (fin) ###########################################################

 */

/**
 * JYOCUtilsJava_Files
 * <p>
 * Utilidades para leer y escribir ficheros binarios y de texto en Java
 *
 * @author Iñaki Martin
 * @version 2
 */
public class JYOCJAVAUtils_Files {

    /**
     * escribirTextoEnFichero
     * <p>
     * Escribe un String en un fichero de texto.
     * El String puede contener varias lineas concartenadas, aunque es un unico String y se guarda en una única linea
     *
     * @param texto          String que se desea escribir
     * @param nombrefichero  nombre del fichero a crear
     * @param pathdelfichero directorio del fichero a crear, si es null se toma el propio directorio del proyecto
     */
    public static void escribirTextoEnFichero(String texto, String nombrefichero, String pathdelfichero) {
        if (pathdelfichero == null) {
            pathdelfichero = "./";
        }
        File fichero = new File(pathdelfichero, nombrefichero);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            pw.println(texto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * leerTextoDeFichero
     * <p>
     * lee un fochero con varias lineas de texto, concatena las lineas en un unico String y devuelve dicho String
     *
     * @param nombrefichero  nombre del fichero a leer
     * @param pathdelfichero directorio del fichero a leer, si es null se toma el propio directorio del proyecto
     * @return el contenido del fichero como un unico String
     */
    public static String leerTextoDeFichero(String nombrefichero, String pathdelfichero) {
        if (pathdelfichero == null) {
            pathdelfichero = "./";
        }
        File fichero = new File(pathdelfichero, nombrefichero);
        BufferedReader br = null;
        FileReader fr = null;
        String textoentero = "";
        try {
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                textoentero = textoentero + linea + "\n";
                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textoentero;
    }

    /**
     * leerLineasDeFichero
     * <p>
     * lee un fichero con varias lineas de texto, y devuevle la lineas en una lista de Strings
     *
     * @param nombrefichero  nombre del fichero a leer
     * @param pathdelfichero directorio del fichero a leer, si es null se toma el propio directorio del proyecto
     * @return el contenido del fichero como una lista de Strings
     */
    public static List<String> leerLineasDeFichero(String nombrefichero, String pathdelfichero) {
        if (pathdelfichero == null) {
            pathdelfichero = "./";
        }
        List<String> resultado = new ArrayList<>();
        File fichero = new File(pathdelfichero, nombrefichero);
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                resultado.add(linea);
                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    /**
     * escribirBytearrayEnFichero
     * <p>
     * Escribe un array de bytes en un fichero binario
     *
     * @param contenido      array de bytes que se desea escribir
     * @param nombrefichero  nombre del fichero a crear
     * @param pathdelfichero directorio del fichero a crear, si es null se toma el propio directorio del proyecto
     */
    public static void escribirBytearrayEnFichero(byte[] contenido, String nombrefichero, String pathdelfichero) {
        if (pathdelfichero == null) {
            pathdelfichero = "./";
        }
        File fichero = new File(pathdelfichero, nombrefichero);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fichero);
            fos.write(contenido);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * leerBytearrayDeFichero
     * <p>
     * lee un array de bytes guardado en un fichero binario
     *
     * @param nombrefichero  nombre del fichero a leer
     * @param pathdelfichero directorio del fichero a leer, si es null se toma el propio directorio del proyecto
     * @return el byte[] leido del fichero
     */
    public static byte[] leerBytearrayDeFichero(String nombrefichero, String pathdelfichero) {
        if (pathdelfichero == null) {
            pathdelfichero = "./";
        }
        File fichero = new File(pathdelfichero, nombrefichero);
        byte[] ficheroenbytes;
        try {
            ficheroenbytes = Files.readAllBytes(fichero.toPath());
            return ficheroenbytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * carpetasHermanasMias
     * <p>
     * Devuelve una List de File, con los
     * hermanos de la carpeta actual que tambien
     * son carpetas
     *
     * @param origen Objeto File que representa el directorio actual
     * @return List de File, con los hermanos de la carpeta actual que tambien son
     * carpetas
     */
    public List<File> carpetasHermanasMias(File origen) {
        File padre = origen.getParentFile();
        File[] hermandad = padre.listFiles();
        List<File> hermanosValidos = new ArrayList<>();
        for (File f : hermandad) {
            if (f.isDirectory()) {
                hermanosValidos.add(f);
            }
        }
        return hermanosValidos;
    }


    public static void guardarObjetoEnFichero(Object objeto, String nombrefichero) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(nombrefichero));
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------------------------------------------------------
    public static Object leerObjetoDeFichero(String nombrefichero) {
        Object o = null;
        File file = new File(nombrefichero);
        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                o = ois.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }


    public static void main(String[] args) {
        Cosa c = new Cosa("BOLI", 12, new Marca("BIC", 23));

        guardarObjetoEnFichero(c, "cosa.bin");

        Cosa cc = (Cosa) leerObjetoDeFichero("cosa.bin");
        System.out.println(cc.nombre);

    }
}


class Cosa implements Serializable {
    String nombre;
    int precio;
    Marca marca;

    public Cosa(String nombre, int precio, Marca marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
    }
}

class Marca implements Serializable {
    String descripcion;
    int ratio;

    public Marca(String descripcion, int ratio) {
        this.descripcion = descripcion;
        this.ratio = ratio;
    }
}

