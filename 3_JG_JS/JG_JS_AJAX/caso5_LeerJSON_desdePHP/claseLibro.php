<?php

class Libro {
    public $codigo_de_libro;
    public $titulo;
    public $autor;
    public $editorial;
    public $precio;

    public function Libro($codigo_de_libro, $titulo, $autor, $editorial, $precio) {
        $this->codigo_de_libro = $codigo_de_libro;
        $this->titulo = $titulo;
        $this->autor = $autor;
        $this->editorial = $editorial;
        $this->precio = $precio;
    }
}