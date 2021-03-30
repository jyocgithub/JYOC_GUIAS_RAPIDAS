package jyoc_java_metodos_utilidades;
import java.io.*;

/*

#######################################################################################
############################  GUIA DE CONTENIDOS ######################################
#######################################################################################
#                                                                                     #
# CONVERSIONES BYTE[] OBJECT (JAVA)                                                   #
# #################################                                                   #
#     - byteArrayToObject                   (byte[] array)                            #
#     - objetoToByteArray                   (Object objeto)                           #
#                                                                                     #
# CONVERSIONES BINARIO DECIMAL HEXADECIMAL                                            #
# ########################################                                            #
#     - deBinarioADecimal      (String numeroBinario)                                 #
#     - deHexadecimalADecimal  (String numeroBinario)                                 #
#     - deDecimalABinario      (int numerodecimal)                                    #
#     - deDecimalAHexadecimal  (int numerodecimal)                                    #
#                                                                                     #
###################################### (fin) ##########################################

 */

public class JYOCJAVAUtils_Conversiones {

    /**
     * objetoToByteArray
     * <p>
     * Convierte un objeto en un array de bytes, por ejemplo, para envio por sockets o guarda en fichero
     * Recordar que exige que la clase que genera el objeto sea SERIALIZABLE
     *
     * @param objeto objeto que deseamos convertir
     * @return un array de bytes fruto del objeto convertido.
     */
    public static byte[] objetoToByteArray(Object objeto) {
        ByteArrayOutputStream baos;
        ObjectOutputStream oos = null;
        try {
            // Escribimos el objeto en un ByteArrayOutpuStream, y de ahi lo convertimos en byte[]
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(objeto);
            oos.flush();
            byte[] array = baos.toByteArray(); // convertimos el objeto escrito en el byte[]
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null; // si se llega aqui es por que hubo un error...
    }

    /**
     * byteArrayToObject
     * <p>
     * Convierte un array de bytes en un objeto, por ejemplo, para envio por sockets o guarda en fichero
     * Recordar que exige que la clase que se genera a partit del objeto sea SERIALIZABLE
     *
     * @param array array de bytes a convertir
     * @return un objeto Object convertido desde el parametro. Normalmente necesitará un casting al tipo real del objeto
     */
    public static Object byteArrayToObject(byte[] array) {
        ByteArrayInputStream byteStream;
        ObjectInputStream ois = null;
        try {
            // Leemos el byte[] de un ByteArrayInpuStream, y de ahi lo convertimos en Object
            byteStream = new ByteArrayInputStream(array);
            ois = new ObjectInputStream(byteStream);
            Object objetoLeido = ois.readObject();
            return objetoLeido;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null; // si se llega aqui es por que hubo un error...
    }


    /**
     * ***************************************************************************
     * ******* CONVERSIONES DE SISTEMA NUMERICO (JAVA)               *************
     * ***************************************************************************
     */

    public static int deBinarioADecimal(String numeroBinario) {
        return Integer.parseInt(numeroBinario, 2);
    }

    public static int deHexadecimalADecimal(String numeroBinario) {
        return Integer.parseInt(numeroBinario, 16);
    }

    public static String deDecimalABinario(int numerodecimal) {
        return Integer.toBinaryString(numerodecimal);
    }

    public static String deDecimalAHexadecimal(int numerodecimal) {
        return Integer.toHexString(numerodecimal);
    }



    /**
     * ***************************************************************************
     * ******* CONVERSIONES DE JSON / STRING                         *************
     * ***************************************************************************
     */

//    /**
//     * convertirAJSON_ObjetoSimple
//     * Crea un json de un objeto simple, cuyas propiedades no contienen colecciones
//     * @param objeto    Objeto a convertir en JSON
//     * @return           JSON creado
//     */
//    public static String convertirAJSON_ObjetoSimple(Libro objeto) {
//        // NECESITA GRADLE O LIBRERIA  : implementation 'com.google.code.gson:gson:2.8.5'
//        // NECESITA SUSTITUIR Libro POR LA CLASE DEL OBJETO QUE CONTIENE EL JSON
//        Gson unGson = new Gson();
//        String cadenaEnFormatoGSON = unGson.toJson(objeto);
//        return cadenaEnFormatoGSON;
//    }
//
//    /**
//     * recuperarDEJSON_ObjetoSimple
//     * Crea un objeto simple a partir de un JSON previamente creado, para objetos simples,
//     * cuyas propiedades no contienen colecciones
//     *
//     * @param cadenaEnFormatoGSON  JSON a convertir
//     * @return  Object         Objeto creado desde el JSON
//     */
//    public static Libro recuperarDEJSON_ObjetoSimple(String cadenaEnFormatoGSON) {
//        // NECESITA GRADLE O LIBRERIA : implementation 'com.google.code.gson:gson:2.8.5'
//        // NECESITA SUSTITUIR Libro POR LA CLASE DEL OBJETO QUE CONTIENE EL JSON
//        Gson unGson = new Gson();
//        Libro objetoRecuperado = unGson.fromJson(cadenaEnFormatoGSON, Libro.class);
//        return objetoRecuperado;
//    }




    // por si alguien quiere probar los metodos previos .......
    public static void main(String[] args) throws IOException {


    }





}
