package jyoc_java_guias.guia_29_socketsUDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ProcesarUDP {
    public static void enviarObjetoUDP(Object objeto, String host, int puerto) {
        try {
            byte[] array = objetoToByteArray(objeto);  // Convertimos el objeto en un array de bytes
            InetAddress direcc = InetAddress.getByName(host);  // Enviamos el byte[] como un Datagram normal
            DatagramPacket dpacket = new DatagramPacket(array, array.length, direcc, puerto);
            DatagramSocket dSocket = new DatagramSocket();
            dSocket.send(dpacket);
            dSocket.close();      // mejor en un finally, aqui ahora por ahorro de espacio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object recibirObjectUDP(String host, int puerto) {
        try {
            byte[] array = new byte[5000]; // Preparar una array de bytes donde recibir un datagrama
            DatagramPacket packet = new DatagramPacket(array, array.length);  // Recibir un datagrama
            InetAddress direcc = InetAddress.getByName(host);
            DatagramSocket dSocket = new DatagramSocket(puerto, direcc);
            dSocket.receive(packet);
            Object objetoLeido = byteArrayToObject(array);  // Convertir el datagrama leido como byte[] en un objeto
            dSocket.close();     // mejor en un finally, aqui ahora por ahorro de espacio
            return objetoLeido;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // si se llega aqui es por que hubo un error...
    }

    public static byte[] objetoToByteArray(Object o) {
        try {
            // Escribimos el objeto en un ByteArrayOutpuStream, y de ahi lo convertimos en byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();
            byte[] array = baos.toByteArray(); // convertimos el objeto escrito en el byte[]
            oos.close();      // mejor en un finally, aqui ahora por ahorro de espacio
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;      // si se llega aqui es por que hubo un error...
    }

    public static Object byteArrayToObject(byte[] array) {
        try {
            // Leemos el byte[] de un ByteArrayInpuStream, y de ahi lo convertimos en Object
            ByteArrayInputStream byteStream = new ByteArrayInputStream(array);
            ObjectInputStream is = new ObjectInputStream(byteStream);
            Object objetoLeido = is.readObject();
            is.close();        // mejor en un finally, aqui ahora por ahorro de espacio
            return objetoLeido;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;      // si se llega aqui es por que hubo un error...
    }
}



