<?php

class Libro{
    
    //Atributos a heredar por la clase Empleado
    public $codigo;
    public $titulo;
    public $autor;
    public $editorial;
    public $precio;
    
    //Constructor => El row lleva los nombres de la base de datos.
    function __construct($row){
        $this->codigo=$row['Codigo_de_libro'];
        $this->titulo=$row['Titulo'];
        $this->autor=$row['Autor'];
        $this->editorial=$row['Editorial'];
        $this->precio=$row['Precio'];
    }
    
    function getCodigo() {
        return $this->codigo;
    }
    function setCodigo($codigo) {
        $this->codigo = $codigo;
    }
    
    function getTitulo() {
        return $this->titulo;
    }
    function setTitulo($titulo) {
        $this->titulo = $titulo;
    }
    
    function getAutor() {
        return $this->autor;
    }
    function setAutor($autor) {
        $this->autor = $autor;
    }
    
    function getEditorial() {
        return $this->editorial;
    }
    function setEditorial($editorial) {
        $this->editorial = $editorial;
    }
    
    function getPrecio() {
        return $this->precio;
    }
    function setPrecio($precio) {
        $this->precio = $precio;
    }

    
}

