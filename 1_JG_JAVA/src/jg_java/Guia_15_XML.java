package jg_java;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;


// FICHERO EXISTENTE CON EL NOMBRE    PERSONAL.XML
//    <datosDePersonal>   // raiz
//
//      <personal>
//          <empleado codigoEmp="c10" codDepto="d10">
//              <nombre>Pepe</nombre>
//              <apellido>Perez</apellido>
//          </empleado>
//
//          <empleado codigoEmp="c11" codDepto="d20">
//              <nombre>Eva</nombre>
//              <apellido>Lopez</apellido>
//          </empleado>
//      </personal>
//
//    </datosDePersonal>


public class Guia_15_XML {


    // **********************************************
    // **********************************************
    // **                                          **
    // **     FICHEROS XML con DOM                 **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |   CREAR NUEVO XML                               |
    //  |                                                 |
    //  +-------------------------------------------------+

    Node nodo_personal, nodo_empleado, nodo_raiz, nodo_nombre, nodo_apellidos, nodo_nombre_texto, nodo_apellidos_texto;
    String ficheroXMLQueSeCrea = "PERSONAL.XML";

    // CUIDADO....... ESTE METODO RECIBE UNA LISTA DE EMPLEADOS SUPUESTAMENTE YA CREADA Y CON EMMPLEADOS
    public void crearDocumentoDOMcondatosJava(ArrayList<Empleado> listaEmp) {

        Document miDocDom; //Objeto Document que almacena el DOM del XML seleccionado.

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            String nombreDelNodoRaiz = "datosDePersonal";
            miDocDom = implementation.createDocument(null, nombreDelNodoRaiz, null);
            miDocDom.setXmlVersion("1.0");

            //-- Crear los nodos de todas las etiquetas generales
            nodo_raiz = miDocDom.getDocumentElement();
            nodo_personal = miDocDom.createElement("personal");

            // recorrer el arraylist de empleados
            for (Empleado em : listaEmp) {

                //-- Crear los nodos de todas las etiquetas del empleado
                nodo_empleado = miDocDom.createElement("empleado");
                nodo_nombre = miDocDom.createElement("nombre");
                nodo_apellidos = miDocDom.createElement("apellidos");

                //-- Crear los nodos de texto con los valores de cada empleado
                nodo_nombre_texto = miDocDom.createTextNode(em.getNombreEmpleado());
                nodo_apellidos_texto = miDocDom.createTextNode(em.getApellidoEmpleado());

                //-- Añade atributos como elementos de texto, esto no crea nodos, modifica los ya creados
                ((Element) nodo_empleado).setAttribute("codigoEmp", em.getCodigoEmpleado());
                ((Element) nodo_empleado).setAttribute("codDptoPertenencia", em.getCodDpto() + "");

                //-- Añadir cada nodo a su padre
                nodo_nombre.appendChild(nodo_nombre_texto);
                nodo_apellidos.appendChild(nodo_apellidos_texto);
                nodo_empleado.appendChild(nodo_nombre);
                nodo_empleado.appendChild(nodo_apellidos);
                nodo_personal.appendChild(nodo_empleado);
            }
            // Se añade el nodo personal a nodo raiz, no se hace dentro del for, pues hay que meter todos los empleados
            // en la etiqueta personal....
            nodo_raiz.appendChild(nodo_personal);


            // FINALMENTE ESCRIBIMOS EL DOCUMENT
            //guardar el documento DOM como un fichero ;
            //Crea un fichero llamado salida.xml
            File archivo_xml = new File(ficheroXMLQueSeCrea);
            //Especifica el formato de salida
            OutputFormat format = new OutputFormat(miDocDom);
            //Especifica que la salida esté indentada.
            format.setIndenting(true);
            //Escribe el contenido en el FILE
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);
            serializer.serialize(miDocDom);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // **********************************************
    // **********************************************
    // **                                          **
    // **     FICHEROS XML con SAX                 **
    // **                                          **
    // **********************************************
    // **********************************************


    //  +-------------------------------------------------+
    //  |   CREAR NUEVO XML                                     |
    //  |                                                 |
    //  +-------------------------------------------------+

    // NECESITAMOS UNA CLASE NUEVA PROPIA. ORIENTADO A LA CLASE ALUMNO QUE ES LO QUE CONTIENE EL XML

    class MiHandlerSax extends DefaultHandler {        // CREA EL HANDLER QUE SE USA LUEGO PARA PARSEAR EL XML
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        Alumno unalumno;
        String etiquetaActual = "";


        @Override
        public void startDocument() throws SAXException {
        }        // esto se ejecuta al comenzar a leer el documento.


        @Override
        public void endDocument() throws SAXException {
        }      // esto se ejecuta al finalizar de leer el documento


        @Override
        public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException {
            // debemos guardarnos qué etiqueta estamos leyendo en cada momento
            etiquetaActual = qname;

            // al emplezar a leer cada elemento, solo me interesan los elementos que tienen atributos
            if (qname.equalsIgnoreCase("alumno")) {
                unalumno = new Alumno();             // como es comienzo de alumno, creo un nuevo alumno para darle datos
                String elId = attributes.getValue("id"); // la etiqueta tiene atributo, asi que lo leo
                if (elId != null) unalumno.setId(elId); // nos aseguramos que el atributo tiene valor
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            // aqui entra en cuanto empieza a leerse un texo dentro de una etiqueta,
            // sabemos que etiqueta estamos leyendo, asi que cogemos el texto...
            String texto = new String(ch, start, length);

            texto.replaceAll("[\n\t]", "");  // eliminamos los caracteres raros

            // y lo asignamos al atributo del alumno que corresponde...
            if (etiquetaActual.equalsIgnoreCase("nombre")) {
                unalumno.setNombre(texto);
            }
            if (etiquetaActual.equalsIgnoreCase("id")) {
                unalumno.setId(texto);
            }
            if (etiquetaActual.equalsIgnoreCase("apellido")) {
                unalumno.setApellidos(texto);
            }
            if (etiquetaActual.equalsIgnoreCase("nota")) {
                unalumno.setNota(Double.parseDouble(texto));
                listaAlumnos.add(unalumno); // como la nota es la ultima etiqueta de un alumno, añadimos el alumno ya creado al arraylist
            }
            etiquetaActual = "";
        }

        @Override
        public void endElement(String uri, String localName, String name) throws SAXException {
        }// aqui entra en cuanto se leer una etiqueta de fin


        public ArrayList<Alumno> getListaAlumnos() throws SAXException {
            return listaAlumnos;
        }
    }

    // Y CON LA CLASE ANTERIOR SOLO HAY QUE EJECUTAR ESTE METODO
    public void leerConSax(String[] args) {

        try {
            MiHandlerSax miHandler = miHandler = new MiHandlerSax();
            // Creamos un analizador de XML con una fábrica de analizadores
            XMLReader xmlreader = XMLReaderFactory.createXMLReader();
            // Añadimos nuestro handler al analizador
            xmlreader.setContentHandler(miHandler);
            // Analizamos con el analizador el xml deseado
            xmlreader.parse(new InputSource(new FileInputStream("alumnosxml.xml")));

            // Probamos que el analizador ha leido el arraylist correctamente
            ArrayList<Alumno> listaalumnos = miHandler.getListaAlumnos();
            for (Alumno a : listaalumnos) {
                System.out.println(a);
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


// ------------------------------------------ CLASES NECESARIAS PARA LOS EJEMPLOS

class Empleado {
    String codigoEmpleado;
    String nombreEmpleado;
    String apellidoEmpleado;
    String codDpto;

    public Empleado() {
    }

    public Empleado(String codigoEmpleado, String nombreEmpleado,
                    String apellidoEmpleado, String codDpto) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.codDpto = codDpto;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public String getCodDpto() {
        return codDpto;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public void setCodDpto(String codDpto) {
        this.codDpto = codDpto;
    }
}


class Alumno {
    private String nombre, apellidos;
    private String id;
    private double nota;

    public Alumno() {
    }

    public Alumno(String nombre, String id, double nota) {
        this.nombre = nombre;
        this.id = id;
        this.nota = nota;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id='" + id + '\'' +
                ", nota=" + nota +
                '}';
    }
}