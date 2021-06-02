package jg_java.guia_29_socketsUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GestorSocketsUDP {

    //  +-------------------------------------------------+
    //  |   ENVIAR CON UDP                                |
    //  +-------------------------------------------------+

    public static void enviarObjetoUDP(Object objeto, String host, int puerto) {
        DatagramSocket dSocket = null;
        try {
            byte[] array = objetoToByteArray(objeto);  // Convertimos el objeto en un array de bytes
            InetAddress direcc = InetAddress.getByName(host);  // Enviamos el byte[] como un Datagram normal
            DatagramPacket dpacket = new DatagramPacket(array, array.length, direcc, puerto);
            dSocket = new DatagramSocket();
            dSocket.send(dpacket);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dSocket != null) dSocket.close();
        }
    }


    //  +-------------------------------------------------+
    //  |    RECIBIR CON UDP                              |
    //  +-------------------------------------------------+

    public static Object recibirObjectUDP(String host, int puerto) {
        DatagramSocket dSocket = null;
        try {
            byte[] array = new byte[5000]; // Preparar una array de bytes donde recibir un datagrama
            DatagramPacket packet = new DatagramPacket(array, array.length);  // Recibir un datagrama
            InetAddress direcc = InetAddress.getByName(host);
            dSocket = new DatagramSocket(puerto, direcc);
            dSocket.receive(packet);
            Object objetoLeido = byteArrayToObject(array);  // Convertir el datagrama leido como byte[] en un objeto
            return objetoLeido;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dSocket != null) dSocket.close();
        }
        return null; // si se llega aqui es por que hubo un error...
    }


    //  +-------------------------------------------------+
    //  |   METODOS DE AYUDA                              |
    //  +-------------------------------------------------+

    public static byte[] objetoToByteArray(Object o) {
        ObjectOutputStream oos = null;
        try {
            // Escribimos el objeto en un ByteArrayOutpuStream, y de ahi lo convertimos en byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            byte[] array = baos.toByteArray(); // convertimos el objeto escrito en el byte[]
            oos.close();      // mejor en un finally, aqui ahora por ahorro de espacio
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
        return null;      // si se llega aqui es por que hubo un error...
    }

    public static Object byteArrayToObject(byte[] array) {

        ObjectInputStream ois = null;
        try {
            // Leemos el byte[] de un ByteArrayInpuStream, y de ahi lo convertimos en Object
            ByteArrayInputStream byteStream = new ByteArrayInputStream(array);
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
        return null;      // si se llega aqui es por que hubo un error...
    }
}



