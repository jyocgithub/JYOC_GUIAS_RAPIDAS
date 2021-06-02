package jg_java;

import java.util.*;

public class Guia_11_COLECCIONES {


    public static void main(String[] args) {

        // ===================================================================
        //   LISTAS          ( ARRAYLIST)     Otras listas: Vector, LinkedList
        // ===================================================================

        ArrayList<String> lista = new ArrayList<>();        // CREAR UN ARRAYLIST VACIO
        ArrayList<Integer> lista2 = new ArrayList<>();

        lista.add("Pepe");             // AÑADIR A ARRAYLIST : la primera posicion es la 0.....
        lista.add("Ana");
        lista.add("Paco");
        lista.add("Luis");
        lista.add("Anastasia ");

        lista.add(3, "Ana");            // INSERTAR EN ARRAYLIST

        lista.set(2, "Eva");           // SUSTITUIR EN ARRAYLIST

        int tam = lista.size();              // TAMAÑO DE ARRAYLIST

        String cc = lista.get(3);               // RECUPERAR UN ELEMENTO DE UN ARRAYLIST

        lista.remove(3);                   //  BORRAR EN UN ARRAYLIST : borra la posicion 3
        lista.remove("Pepe");               //  BORRAR EN UN ARRAYLIST : borra SOLO LA PRIMERA ocurrencia de Pepe

        if (lista.contains("Ana")) {              // CONSULTAR SI EXIST UN ELEMENTO EN UN ARRAYLIT
            // ....
        }

        lista.clear();                              // VACIAR UN ARRAYLIST

        for (int i = 0; i < lista.size(); i++) {  // RECORRER UN ARRAYLIST
            String ss = lista.get(i);
            System.out.println(ss);
        }

        for (String s : lista) {                  // RECORRER UN ARRAYLIST FOR EACH
            System.out.println(s);
        }


        // ==============================================================================
        //   SETS           ( TREESET )         Otros conjuntos: HashSet, LinkedHashSet
        // ==============================================================================

        TreeSet<String> conj = new TreeSet<>(); // CREAR UN ARRAYLIST VACIO
        HashSet<Avion> conj2 = new HashSet<>(); // objeto piso

        conj.add("Pepe");                       // AÑADIR A UN TREESET . No hay posicion
        conj.add("Ana");
        conj.add("Luis");
        conj.add("Juan");
        conj.add("Lola");
        conj.add("Carlos");

        int tam2 = conj.size();             // TAMAÑO DE TREESET

        conj.remove("Pepe");                  //  BORRAR EN UN TREESET : borra la única ocurrencia de Pepe

        for (String s : conj) {                // RECORRER UN TREESET FOR EACH
            System.out.println(s);             // Al recorrer con for each, SOLO SI ES un treeset, SALEN ORDENADOS
        }                                      // pero NO VALE PARA CLASES PROPIAS si no tienen un comparador


        if (conj.contains("Ana")) {              // CONSULTAR SI EXIST UN ELEMENTO EN UN TREESET
            // ....
        }

        conj.clear();     // VACIAR UN TREESET


        // ==============================================================================
        //   MAPAS           ( HASHMAP )         Otros conjuntos: TreeMap, HashTable
        // ==============================================================================

        // MAPAS  - almacenan DOS COLECCIONES; la clave de un elemento y el valor del elemento
        // ejemplo - guardo el nombre de un jugador y como clave su numero de dorsal
        TreeMap<Integer, String> mapa = new TreeMap<>();

        mapa.put(12, "JUANILLO");           // AÑADIR A UN TREESET . No hay posicion
        mapa.put(34, "PAQUITO");

        int tama = mapa.size();             // TAMAÑO DE TREESET

        String nom = mapa.get(34);           // RECUPERAR UN ELEMENTO DE UN ARRAYLIST

        mapa.remove(12);                 //  BORRAR EN UN TREESET : borra la única ocurrencia de Pepe

        mapa.clear();                      // VACIAR UN TREESET

        if (mapa.containsKey(33)) {               // CONSULTAR SI EXIST UNA CLAVE EN UN TREESET
            // ....
        }
        if (mapa.containsValue("PACO")) {           // CONSULTAR SI EXIST UN VALOR EN UN TREESET
            // ....
        }

        for (Integer clave : mapa.keySet()) {    // RECORRER UN MAPA: RECORRER LAS CLAVES
            String valor = mapa.get(clave);
        }
        for (String valor : mapa.values()) {    // RECORRER UN MAPA: RECORRER LOS VALORES

        }

        // ==============================================================================
        //   ITERATOR
        // ==============================================================================

        ArrayList<Avion> aviones = new ArrayList<>();    // ITERATOR con un ARRAYLIST
        Iterator<Avion> it = aviones.iterator();
        while (it.hasNext()) {
            Avion a = it.next();
            if (a.getNummotores() > 3) {
                it.remove();
            }
        }

        TreeSet<Avion> avioncillos = new TreeSet<>(); // ITERATOR con un ARRAYLIST
        Iterator<Avion> iter = avioncillos.iterator();
        while (iter.hasNext()) {
            Avion a = iter.next();
            if (a.getNummotores() > 3) {
                iter.remove();
            }
        }

        TreeMap<Integer, String> mimapa = new TreeMap<>();
        Iterator<Integer> it3 = mimapa.keySet().iterator(); // ITERATOR con un MAPAS: recorrer las CLAVES
        while (it3.hasNext()) {
            int clave = it3.next();
            // ....
        }
        Iterator<String> it4 = mimapa.values().iterator();  // ITERATOR con un MAPAS: recorrer los VALORES
        while (it4.hasNext()) {
            String valor = it4.next();
            // ....
        }



        // ==============================================================================
        //   ENTRY.SET
        // ==============================================================================
        HashMap<String , Avion> mapaaviones = new HashMap<>();
        mapaaviones.put("1",new Avion());

        for ( Map.Entry<String , Avion>  cosa : mapaaviones.entrySet() ){
            System.out.println( " la clave es " + cosa.getKey());
            System.out.println( " el valor es  " + cosa.getValue().toString());
        }


    }

}

//Clases auxiliares para los ejemplos
class Avion {

    private String nombre;
    private int nummotores;

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

    @Override
    public String toString() {
        return "guias_java.Avion{" +
                "nombre='" + nombre + '\'' +
                ", nummotores=" + nummotores +
                '}';
    }
}
