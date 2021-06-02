package jg_java.guia_26_base_de_Datos;



import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class GestorBBDD {

    String conexionDB = "jdbc:mysql://localhost:3306/";  // esto es para usar MySql
    String nombreBD = "tarea11";  // aqui viene el nombre de la base de datos
    String usuarioDB = "root";  // usuariode bbdd
    String passwordDB = "";   // pwd de la bbdd
    String opcionesBD = "";  // opcional
    Connection miConexion;

    /**
     * CONECTAR
     * Establece una conexion a la bbdd y la asigna al atributo conexion
     * Usa los atributos antes indicados para configurar correctamente la conexion
     */
    public void conectar() {
        try {
            miConexion = DriverManager.getConnection(conexionDB + nombreBD + opcionesBD, usuarioDB, passwordDB);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * DESCONECTAR
     */
    public void desconectar() {
        try {
            miConexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * insertarPelicula
     *
     * @param miPelicula guias_java.Pelicula que se quiere dar de alta en la bbdd
     */
    public void insertarPelicula(Pelicula miPelicula) {
        conectar();
        try {
            String sql = "INSERT INTO PELICULA (COD_PELI, TITULO, FECHAESTRENO ) VALUES ( ?,?,?)  ";

            PreparedStatement instruccion = miConexion.prepareStatement(sql);
            instruccion.setInt(1, miPelicula.cod_peli);
            instruccion.setString(2, miPelicula.getTitulo());

            java.util.Date fechautil = miPelicula.getFechaestreno();
            java.sql.Date fechasql = dateUTILtoSQL(fechautil);
            instruccion.setDate(3, fechasql);

            instruccion.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
    }

    /**
     * consultarPeliculas
     *
     * @return un arraylist con las peliculas existentes
     */
    public ArrayList<Pelicula> consultarPeliculas() {
        conectar();

        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PELICULA ";

            PreparedStatement instruccion = miConexion.prepareStatement(sql);

            ResultSet registros = instruccion.executeQuery();

            while (registros.next()) {
                int cod = registros.getInt("cod_peli");
                String titulo = registros.getString("titulo");
                // no es necesario convertir de java.sql.Date a java.util.Date
                Date fechaestreno = registros.getDate("fechaestreno");

                Pelicula nuevaPelicula = new Pelicula(cod, titulo, fechaestreno);

                peliculas.add(nuevaPelicula);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();

        }
        return peliculas;
    }

    /**
     * @param codigobuscado
     * @return
     */
    public ArrayList<Pelicula> consultarUnaPeliculas(int codigobuscado) {
        conectar();

        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PELICULA WHERE COD_PELI  = ? ";

            PreparedStatement instruccion = miConexion.prepareStatement(sql);
            instruccion.setInt(1, codigobuscado);

            ResultSet registros = instruccion.executeQuery();

            while (registros.next()) {
                int cod = registros.getInt("cod_peli");
                String titulo = registros.getString("titulo");
                // no es necesario convertir de java.sql.Date a java.util.Date
                Date fechaestreno = registros.getDate("fechaestreno");

                Pelicula nuevaPelicula = new Pelicula(cod, titulo, fechaestreno);

                peliculas.add(nuevaPelicula);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();

        }
        return peliculas;
    }

    /**
     * @param codigobuscado
     * @return
     */
    public boolean borrarUnaPeliculas(int codigobuscado) {
        conectar();

        Pelicula pelicula = null;
        try {
            String sql = "DELETE FROM PELICULA WHERE COD_PELI = ? ";

            PreparedStatement instruccion = miConexion.prepareStatement(sql);
            instruccion.setInt(1, codigobuscado);

            int f = instruccion.executeUpdate();
            if (f == 1) {

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
        return false;
    }


    public void altaTablas() {
        String sqlCrearTablaPelicula = "CREATE TABLE IF NOT EXISTS pelicula ( "
                + "cod_peli int NOT NULL ,  "
                + "titulo varchar(20) , "
                + "fechaestreno DATE , "
                + "PRIMARY KEY (cod_peli))";

        conectar();
        try {
            PreparedStatement instruccion = miConexion.prepareStatement(sqlCrearTablaPelicula);
            instruccion.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
    }


    /**
     * public void modificarUnaPelicula(guias_java.Pelicula miPelicula) {
     *
     * @param miPelicula
     */
    public void modificarUnaPelicula(Pelicula miPelicula) {
        conectar();
        try {
            String sql = "UPDATE PELICULA SET titulo=?, FECHAESTRENO=?  ";

            PreparedStatement instruccion = miConexion.prepareStatement(sql);
            instruccion.setString(1, miPelicula.getTitulo());

            java.util.Date fechautil = miPelicula.getFechaestreno();
            java.sql.Date fechasql = dateUTILtoSQL(fechautil);
            instruccion.setDate(2, fechasql);

            instruccion.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            desconectar();
        }
    }


    /**
     * @return
     */
    public int getMaxIdPelicula() {
        try {
            String sql = "SELECT max(id_peli) FROM pelicula";
            ResultSet rs = miConexion.createStatement().executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            return 0;
        }
    }


    public static java.sql.Date dateUTILtoSQL(java.util.Date fechaEnUtil) {
        if (fechaEnUtil == null) {
            return null;
        }
        java.sql.Date fechaEnSql = null;
        fechaEnSql = new java.sql.Date(fechaEnUtil.getTime());
        return fechaEnSql;
    }
}
