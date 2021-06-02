package jg_java.guia_30_criptografia.cifrados_des_aes_rsa_firma;

import sun.security.provider.SHA;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


public class GestorCifrados {

    // **********************************************
    // **********************************************
    // **                                          **
    // **      CIFRADOS SIMETRICOS                 **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |   SIMETRICOS - CIFRADO CON DES                  |
    //  +-------------------------------------------------+

    public static byte[] mensajeCifrado = null;
    public static Cipher aes = null;
    public static Key key = null;

    public static void conDES_cifrarFicheroTexto(String param_nombreficheroentrada, String param_nombreficherosalida, String param_nombreFicheroClaveQueSeVaACrear) {
        // Creamos un objeto KeyGenerator asociado al algoritmo de encriptado a utilizar
        // (DES en este caso, algoritmo simétrico)
        // KeyGenerator  y las clases siguientes de procesado de encriptación son clases que ofrece
        // Java como parte de las Extensiones Criptográficas Java (JCE)
        KeyGenerator obKeyGenerator;
        try {
            obKeyGenerator = KeyGenerator.getInstance("DES");


            // Generamos la clave con el método generateKey()
            SecretKey obSecretKey = obKeyGenerator.generateKey();

            // Se crea un objeto Cipher, para efectuar encriptados y desencriptados.
            // Se crea asociándolo al algoritmo de encriptado a utilizar
            // (DES en este caso, algoritmo simétrico)
            Cipher obCipher = Cipher.getInstance("DES");

            // Se configura el objeto Cipher, indicándole el modo (encriptar/desencriptar,
            // encriptar en este caso) y pasándole como parámetro la clave de encriptacion
            // creada anteriormente.
            obCipher.init(Cipher.ENCRYPT_MODE, obSecretKey);

            // Abrimos el fichero fuente que deseamos encriptar y creamos un stream para su lectura
            File fileEntrada = new File(param_nombreficheroentrada);
            FileInputStream fisEntrada = new FileInputStream(fileEntrada);

            // Abrimos el fichero destino donde escribiremos el fichero fuente encriptado
            // y creamos un stream para su escritura
            File fileSalida = new File(param_nombreficherosalida);
            if (!fileSalida.exists()) {
                fileSalida.createNewFile();
            }
            FileOutputStream fosSalida = new FileOutputStream(fileSalida);

            // Leemos todo el contenido del fichero de entrada,
            // para lo cual se define un tamaño de bloque de lectura de 8 bytes y se recorre
            // el fichero de entrada bloque a bloque.
            // Para cada bloque, se efectúa la escritura del mismo ya encriptada usado el método
            // doFinal() del objeto Cypher
            byte[] buffer = new byte[8];
            int leidos = fisEntrada.read(buffer);
            while (leidos != -1) {
                fosSalida.write(obCipher.doFinal(buffer, 0, leidos));
                leidos = fisEntrada.read(buffer);
            }
            fisEntrada.close();
            fosSalida.close();
            // Se procede a crear un fichero con la clave de encriptado usada.
            // Para ello se crea primero un objeto de la factoría de claves SecretKeyFactory
            SecretKeyFactory keyfac = SecretKeyFactory.getInstance("DES");

            // Con el objeto anterior, y con su método getKeySpec se obtiene la información de
            // la especificación de la clave usada, que se guarda en un objeto de DESKeySpec
            DESKeySpec obDESKeySpec = (DESKeySpec) keyfac.getKeySpec(obSecretKey, DESKeySpec.class);

            // Se crea un fichero para escribir la clave,
            File fichclave = new File(param_nombreFicheroClaveQueSeVaACrear);
            if (!fichclave.exists()) fichclave.createNewFile();
            FileOutputStream fosclave = new FileOutputStream(fichclave);

            // Se escribe dicha clave tras obtenerla a través de la especificación obtenida en el objeto DESKeySpec, con su método getKey()
            fosclave.write(obDESKeySpec.getKey());
            fosclave.close();
            System.out.println("Cifrado completo¡");


        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | NoSuchPaddingException e) {
            e.printStackTrace();
        }


    }

    public static void conDES_descifrarFicheroTexto(String param_nombreficheroentrada, String param_nombreficherosalida, String param_nombreFicheroClavePreviamenteObtenida) {

        try {

            // Abrimos el fichero con la clave, y lo leemos con un objeto que hace stream,
            // de la clase FileInputStream.
            // La lectura la metemos en un array de bytes que llamamos arrayByteConClave
            File fichclave = new File(param_nombreFicheroClavePreviamenteObtenida);
            FileInputStream fisClave = new FileInputStream(fichclave);
            byte[] arrayByteConClave = new byte[(int) fichclave.length()];
            fisClave.read(arrayByteConClave);
            fisClave.close();
            // Creamos un objeto de especificación de claves con la clase DESKeySpec.
            DESKeySpec obDESKeySpec = new DESKeySpec(arrayByteConClave);

            // Creamos una clave de desencriptacion, que es un objeto de SecretKey,
            // con el método generateSecret() de una factoria SecretKeyFactory.
            // A este método le pasamos la especificación de claves creada anteriormente.
            SecretKeyFactory keyfac = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyfac.generateSecret(obDESKeySpec);

            // Se configura el objeto Cipher, indicándole el mono (encriptar/desencriptar,
            // desencriptar en este caso) y pasándole como parámetro la clave de encriptacion creada anteriormente.
            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.DECRYPT_MODE, key);

            // Abrimos el fichero encriptado y le asignamos un stream de lectura.
            File fileEntrada = new File(param_nombreficheroentrada);
            FileInputStream fisEntrada = new FileInputStream(fileEntrada);

            // Creo un fichero de salida y le asigno un stream de escritura.
            File fileSalida = new File(param_nombreficherosalida);
            if (!fileSalida.exists()) fileSalida.createNewFile();
            FileOutputStream fosSalida = new FileOutputStream(fileSalida);

            // Leemos todos el contenido del fichero de entrada, para lo cual se define un tamaño de
            // bloque de lectura de 16 bytes y se recorre el fichero de entrada bloque a bloque.
            // Para cada bloque, se desencripta usado el método doFinal() del objeto Cypher antes descrito,
            // y se efectúa la escritura del bloque desencriptado en el fichero destino.
            byte[] buffer = new byte[16];
            int bytes_leidos = fisEntrada.read(buffer);
            while (bytes_leidos != -1) {
                fosSalida.write(desCipher.doFinal(buffer, 0, bytes_leidos));
                bytes_leidos = fisEntrada.read(buffer);
            }
            fisEntrada.close();
            fosSalida.close();

        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        System.out.println("Descifrado completo.");

    }

    //  +-------------------------------------------------+
    //  |    SIMETRICOS - CIFRADO CON AES                 |
    //  +-------------------------------------------------+

    public static String conAES_cifrarString(String mensajeoriginal) {
        KeyGenerator obKeyGenerator;
        String solucion = null;
        String stringMensajeCifrado = null;
        try {

            ///////////////////////////////////////////////////////////////////////////
            // Cifrado y descifrado sencillo de un solo mensaje de texto
            // usamos ahora AES en vez de DES, son ambos simetricos
            // Generamos una clave de 128 bits adecuada para AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            key = keyGenerator.generateKey();
            // o, alternativamente, podemos usar una clave que queramos que tenga al menos 16 bytes
            // y nos quedamos con los bytes 0 a 15
            key = new SecretKeySpec("una clave de 16 bytes".getBytes(), 0, 16, "AES");

            // Se obtiene un cifrador AES
            aes = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Se inicializa para encriptacion y se encripta el texto,
            // que debemos pasar como bytes.
            aes.init(Cipher.ENCRYPT_MODE, key);
            mensajeCifrado = aes.doFinal(mensajeoriginal.getBytes());

        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        return new String(mensajeCifrado);
    }

    public static String conAES_descifrarString(String mensajeoriginal) {
        String solucion = null;
        try {
            // Se iniciliza el cifrador para desencriptar, con la misma clave y se desencripta
            aes.init(Cipher.DECRYPT_MODE, key);
            byte[] mensajeDesencripado = aes.doFinal(mensajeCifrado);
//            System.out.println("Mensaje cifrado en hexadecimal; ");
//            for (byte b : mensajeCifrado) {
//                System.out.print(Integer.toHexString(0xFF & b));
//            }
            solucion = new String(mensajeDesencripado);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return solucion;
    }


    // **********************************************
    // **********************************************
    // **                                          **
    // **      CIFRADOS ASIMETRICOS                **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |    ASIMETRICOS - CIFRADO CON RSA                |
    //  +-------------------------------------------------+

    static private PublicKey obClavePublica;
    static private PrivateKey obClavePrivada;
    static private Cipher encriptador;

    public static void conRSA_cifrarFichero(String param_nombreficheroentrada, String param_nombreficherosalida, String param_nombreficheroClave) {

        try {
            encriptador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // ===> Se deben crear las DOS CLAVES, usando la PUBLICA para encriptar, y la PRIVADA para desencriptar
            generarClavePrivadaYPublicaRSA();
            // se necesitar escribir la clave privada en un fichero, pues es lo que va a usar posteriormente el desencriptado
            escribirBytearrayEnFichero(obClavePrivada.getEncoded(), param_nombreficheroClave);
            File file = new File(param_nombreficheroentrada);
            if (file.exists()) {
                try {
                    String textosinencriptar = leerTextoDeUnFichero(param_nombreficheroentrada);

                    // para encriptar hemos de usar la clave PUBLICA creada al principio
                    // ==> el enccriptador se activa en modo ENCRYPT con la CLAVE PUBLICA
                    encriptador.init(Cipher.ENCRYPT_MODE, obClavePublica);
                    byte[] cifradoEnBytearray = encriptador.doFinal(textosinencriptar.getBytes());
                    escribirBytearrayEnFichero(cifradoEnBytearray, param_nombreficherosalida);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No existe ese fichero que quiere encriptar");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void conRSA_descifrarFichero(String param_nombreficheroentrada, String param_nombreficherosalida, String param_nombreficheroClavePrivada) {
        File file = new File( param_nombreficheroentrada);
        if (file.exists()) {
            try {
                // leer fichero a desencriptar
                byte[] ficheroPreviamenteCifrado_EnBytearray = leerFicheroAUnBytearray(param_nombreficheroentrada);

                // para desencriptar hemos de usar la clave privada creada al encriptar, y podemos hacer dos cosas:
                // 1.- usar directamente el objeto creado, si lo tenemos
                encriptador.init(Cipher.DECRYPT_MODE, obClavePrivada);
                // 2.- leer la clave privada de un fichero, si la almacenamos previamente
//                byte[] clavePrivadaEnBytearray = leerFicheroAUnBytearray(param_nombreficheroClavePrivada, ".");
//                KeyFactory kf = KeyFactory.getInstance("RSA");
//                PrivateKey obClavePrivadaleida = kf.generatePrivate(new PKCS8EncodedKeySpec(clavePrivadaEnBytearray));
//                encriptador.init(Cipher.DECRYPT_MODE, obClavePrivadaleida);

                // ==> el encriptador se activa en modo DECRYPT con la CLAVE PRIVADA
                byte[] descifradoEnBytearray = encriptador.doFinal(ficheroPreviamenteCifrado_EnBytearray);

                String textodesencriptado = new String(descifradoEnBytearray);
                System.out.println("Fichero leido tras desencriptarlo:\n" + textodesencriptado);
                escribirTextoEnFichero(textodesencriptado, param_nombreficherosalida);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No existe ese fichero que quiere desencriptar");
        }
    }


    public static void generarClavePrivadaYPublicaRSA() {
        KeyPairGenerator keyPairGenerator;
        KeyPair keyPair;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPair = keyPairGenerator.generateKeyPair();
            obClavePublica = keyPair.getPublic();
            obClavePrivada = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    // **********************************************
    // **********************************************
    // **                                          **
    // **      CIFRADOS RESUMEN (HASH)             **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |   CIFRADO CON HASH MID5                         |
    //  +-------------------------------------------------+

    public static String conHASH_encriptarString(String mensaje_original) {
        // MD5 permite encriptar una frase, pero no permite desencriptarla.
        // Es útil para guardar password con encriptación MD5 en una base de datos.
        // Cuando un usuario introduce su clave, el código la encripta con MD5
        // y compara con la que hay en base de datos.
        // De esta forma, la clave en claro (original, legible)
        // no está almacenada en ningún sitio y tampoco es posible
        // recuperarla a partir de la encriptada.

        try {
            // Creamos un objeto MessageDigest asociado al algoritmo de encriptado a
            // utilizar (HASH-MD5 de 64 bits en este caso)
            // MessageDigest y las clases siguientes de procesado de encriptación son clases
            // que ofrece Java como parte de las Extensiones Criptográficas Java (JCE)

            // El primer parametro de getInstance() es el nombre del algoritmo digest a
            // usar:"MD5", "MD4", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512",
            // "RIPEMD128", "RIPEMD160"...
            // Puede haber un segundo parametro con el proveedor de seguridad
            MessageDigest md = MessageDigest.getInstance("MD5");

            // crear el resumen con el método digest();
            // el resumen es un array de 16 bytes
            byte[] messageDigest = md.digest(mensaje_original.getBytes());

            // convertimos el array de bytes en un numero (muy grande, de 39 digitos o asi)
            // con signo positivo (eso dice el parámetro con valor 1)
            BigInteger number = new BigInteger(1, messageDigest);

            // convertimos el numero grande a hexadecimal
            // el resutlado es un string de longitud 32
            String hashtexthexadecimal = number.toString(16);

            // si no han salido 32 chars, añadimos ceros por delante hasta
            // que la longitud sea 32
            while (hashtexthexadecimal.length() < 32) {
                hashtexthexadecimal = "0" + hashtexthexadecimal;
            }
            return hashtexthexadecimal;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void conHASH_encriptarFicheroTexto(String param_nombreficheroentrada, String param_nombreficherosalida) {
        try {

            // Este modelo leee un fichero con varias lineas
            // y crea un resumen de todas ellas
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Abrimos un fichero para leer su contenido
            File file = new File(param_nombreficheroentrada);
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Leemos linea a linea el fichero,
            // y las vamos almacenando acumulándolas en el objeto MessageDigest
            String linea;
            linea = br.readLine();
            while (linea != null) {
                md.update(linea.getBytes());
                linea = br.readLine();
            }
            // Crear el resumen con el método digest();
            byte[] resumen = md.digest();

            // Primero, para confirmar, convertimos el resumen en string ylo escribimos directamente en pantalla
            for (byte b : resumen) {
                System.out.print(Integer.toHexString(0xFF & b));
            }

            // Y ademas guardamos en un fichero el encriptado realizado:
            // Creo un fichero de salida y le asigno un stream de escritura.
            File fileSalida = new File(param_nombreficherosalida);
            if (!fileSalida.exists()) fileSalida.createNewFile();
            FileOutputStream fosSalida = new FileOutputStream(fileSalida);
            fosSalida.write(resumen);
            fosSalida.close();

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // **********************************************
    // **********************************************
    // **                                          **
    // **              FIRMA DIGITAL               **
    // **                                          **
    // **********************************************
    // **********************************************

    //--------------------- AVISO -----------------------------------------------------------------------------------
    //  Desde la version JDK 8u151 (mediados 2017)  el tamaño de clave predeterminado cambió de 1024 a 2048 bits .
    //  Si se usan versiones dispares de JDK, se puede obtener una excepción:
    //    "InvalidKeyException: The security strength of SHA-1 digest algorithm is not sufficient for this key size"
    //  Puedde que se haya creado una clave con longitud 1024 y se intente verificar con procesos que esperan 2048 bits
    //---------------------------------------------------------------------------------------------------------------
    static private PrivateKey obClavePrivadaParaFirmaDigital;
    static private PublicKey obClavePublicaParaFirmaDigital;

    public static byte[] firmarDigitalmente_UnTexto(String mensajeAFirmar) {
        byte[] firma = null;
        try {

            // crea un par de claves, y guardarlas. Una se usa al firmar y otra al verificar
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
            KeyPair keypair = keygen.generateKeyPair();
            obClavePrivadaParaFirmaDigital = keypair.getPrivate();
            obClavePublicaParaFirmaDigital= keypair.getPublic();

            // crear un objeto Signature
            Signature signature = Signature.getInstance("DSA");
            // iniciar el proceso de firmar (initsign) aplicando la clave PRIVADA
            signature.initSign(obClavePrivadaParaFirmaDigital);

            // preparar el mensaje original para ser firmado
            signature.update(mensajeAFirmar.getBytes());

            // firmar el mensaje y obtener la firma
            firma = signature.sign();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Finalmetne devolvemos la firma creada
        return firma;
    }

    public static void verificarDigitalmente_UnTexto(String mensajeAVerificar, byte[] firmaObtenidaPreviamente) {
        try {
            // NO debemos crear nuevas claves, sino usar la clave PUBLICA del par de claves que se creo al firmar

            // crear objeto Signature
            Signature signature = Signature.getInstance("DSA");

            // iniciar el proceso de verificacion (initVerify) aplicando la clave PUBLICA
            signature.initVerify(obClavePublicaParaFirmaDigital);

            // preparar el mensaje original para ser verificado
            signature.update(mensajeAVerificar.getBytes());

            // verificamos el mensaje original contra la firma creada para el previamente
            if (signature.verify(firmaObtenidaPreviamente)) {
                System.out.println("El mensaje es auténtico :-)");
            }else{
                System.out.println("El mensaje no se ha confirmado como original !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    // **********************************************
    // **********************************************
    // **                                          **
    // **      UTILIDADES VARIAS                   **
    // **                                          **
    // **********************************************
    // **********************************************

    public static void escribirBytearrayEnFichero(byte[] contenido, String nombrefichero) {
        File fichero = new File(nombrefichero);

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
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] leerFicheroAUnBytearray(String nombrefichero) {
        File fichero = new File( nombrefichero);
        byte[] ficheroenbytes;
        try {
            ficheroenbytes = Files.readAllBytes(fichero.toPath());
            return ficheroenbytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void escribirTextoEnFichero(String texto, String nombrefichero) {
        File fichero = new File( nombrefichero);
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

    public static String leerTextoDeUnFichero(String nombrefichero) {
        File fichero = new File( nombrefichero);
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
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return textoentero;
    }


}
