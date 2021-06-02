<?php

// CONSTRUCTOR POR DEFECTO
// =======================
class Perro
{
    
    private $nombre;    
    // Métodos accesorios (getters setters)
    // ...de escritura
    function setNombre($n)
    {
        $this->nombre = $n;
    }
    
    //.. de lectura
    function getNombre()
    {
        return $this->nombre;
    }
}


$p1 = new Perro();
$p1->setNombre("BLAS");
echo $p1->getNombre();



// CONSTRUCTOR PROPIO
// =======================
class Persona
{
    // Declaración del campo
    $apellidos = "";    // visible en cualquier sitio. valor por defecto
    public $nombre;    // visible en cualquier sitio. valor por defecto
    private $mote;    // visible solo en la clase 
    protected $edad;  // visible en la clase y sus herederos
    
    //Constructor
    // function Persona($nom)  // ESTE MODO ESTA OBSOLETO DESDE PHP 8
    function __construct($nom)
    {
        $this->nombre = $nom;
    }
    
    // Métodos accesorios (getters setters)
    // ...de escritura
    function setNombre($n)
    {
        $this->nombre = $n;
    }
    
    //.. de lectura
    function getNombre()
    {
        return $this->nombre;
    }
    
    // otros métodos
    public function Saluda($saludo)
    {
        echo $saludo . "<br>";
    }
}

$objPersona = new Persona("Rubén");
echo "<p>El objeto <span style='color:red; font-weight:bold'> objPersona</span> se llama " .  $objPersona->getNombre() . "</p>";
$objPersona->Saluda("Que pasa por ahi!");

// HERENCIA
// =======================
class Estudiante extends Persona
{
    private $carrera;
    
    function Estudiante($nom, $carr)
    {
        parent::__construct($nom);  // LLAMADA AL CONSTRUCTOR MATERNO
        $this->carrera = $carr;
    }
    
    function  setCarrera($a)
    {
        $this->carrera = $a;
    }
    
    function  getCarrera()
    {
        return $this->carrera;
    }
    
    function daExamen($nomExamen, $nota)
    {
        return "En el examen de la asignatura <b>" . $nomExamen . "</b> tiene una nota de <b>" . $nota . "</b>";
    }
}
