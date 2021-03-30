package jyoc_java_guias.guia_26_base_de_Datos;

import java.util.Date;

/**
 * Clases auxiliares para los ejemplos
 */

class Pelicula {
    int cod_peli;
    String titulo;
    Date fechaestreno;

    public Pelicula(int cod_peli, String titulo, Date fechaestreno) {
        this.cod_peli = cod_peli;
        this.titulo = titulo;
        this.fechaestreno = fechaestreno;
    }

    public int getCod_peli() {
        return cod_peli;
    }

    public void setCod_peli(int cod_peli) {
        this.cod_peli = cod_peli;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaestreno() {
        return fechaestreno;
    }

    public void setFechaestreno(Date fechaestreno) {
        this.fechaestreno = fechaestreno;
    }
}
