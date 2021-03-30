package jyoc_java_guias.guia_30_criptografia.cifrados_des_aes_rsa_firma;

public class Pruebas {


    public static void main(String[] args) {


//        pruebaDES_FicheroTexto();
  //      pruebaAES_Texto();
//        pruebaHASH1();
//        pruebaHASH2();

        pruebaRSA_FicheroTexto();
 //       prueba_firmadigital();

    }

    public static void pruebaDES_FicheroTexto(){

        GestorCifrados.conDES_cifrarFicheroTexto("sinencriptarDES.txt", "yaencriptadodes.txt", "claveDESobtenida.txt");
        GestorCifrados.conDES_descifrarFicheroTexto("yaencriptadodes.txt", "desencriptadoDES.txt", "claveDESobtenida.txt");

    }

    public static void pruebaAES_Texto(){

        String res = GestorCifrados.conAES_cifrarString("Texto de entrada muy optimista");
        String res2 = GestorCifrados.conAES_descifrarString(res);
        System.out.println(res);
        System.out.println("el texto desencriptado es : "+ res2);

    }

    public static void pruebaHASH1(){

        // primera vez que se crea el texto
        String res = GestorCifrados.conHASH_encriptarString("paco en la luna");
        System.out.println(res);

        // segunda vez que se crea el texto
        String res2 = GestorCifrados.conHASH_encriptarString("paco en la luna");
        System.out.println(res);

        if(res.equals(res2)){
            System.out.println("son iguales !!");
        }else{
            System.out.println("Upps, algo ha ido mal");
        }

    }
    public static void pruebaHASH2(){

        GestorCifrados.conHASH_encriptarFicheroTexto("sinencriptarHASH.txt", "yaencriptadoHASH.bin");

    }


    public static void pruebaRSA_FicheroTexto(){

        GestorCifrados.conRSA_cifrarFichero("sinencriptarRSA.txt", "yaencriptadoRSA.txt", "laclavepublica.bin");
        GestorCifrados.conRSA_descifrarFichero( "yaencriptadoRSA.txt", "desencriptadoRSA.txt", "laclavepublica.bin");

    }

    public static void prueba_firmadigital(){


        byte[] firmaobtenida = GestorCifrados.firmarDigitalmente_UnTexto("texto que vamos a usar de prueba");
        GestorCifrados.verificarDigitalmente_UnTexto( "texto que vamos a usar de prueba", firmaobtenida);

    }

}

