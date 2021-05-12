<?php
require_once("include/libro.php");

class DB
{

    private $ip = 'localhost:3306';  // 3306 por que se usa Mysql desde fuera de PHPMyAdmin, si es PHPMyADmin, quitar el puerto
    private $basededatos = 'JYOC_PEDIDOS';
    private $usuario = 'root';
    private $password = 'root';
    private $codificacion = "SET CHARACTER SET UTF8";
    private $opciones = [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_CASE => PDO::CASE_NATURAL
    ];

    private function conectar()
    {
        try {
            $conexion = new PDO("mysql:host=$this->ip; dbname=$this->basededatos", $this->usuario, $this->password, $this->opciones);
            $conexion->exec($this->codificacion);
        } catch (Exception $ex) {
            die('Conexion fallida a la bbdd: ' . $ex->getMessage());
        }

        return $conexion;
    }

    private  function ejecutarQuery($sql)
    {
        // $conexion = self::conectar();  // si el metodo fuera estatico usariamos esto
        $conexion = $this->conectar();
        $resultado = null;
        if (isset($conexion)) {
            $resultado = $conexion->query($sql);
        }
        return $resultado;
    }
    
    public  function getLibros()
    {
        $sql = "SELECT * FROM LIBROS";
        $resultado = $this->ejecutarQuery($sql);
        $lista = array();
        if (isset($resultado)) {
            $row = $resultado->fetch();
            if ($row !== false) {
                while ($row != null) {
                    $lista[] = new Libro($row);
                    $row = $resultado->fetch();
                }
            }
        }
        return $lista;
    }
    public  function getLibroPorCodido($codpedido)
    {
         $sql = "SELECT * FROM LIBROS WHERE Codigo_de_libro = '" . $codpedido . "';";
        // $sql = "SELECT * FROM LIBROS ";
        $resultado = $this->ejecutarQuery($sql);
        $libro = null;
        if (isset($resultado)) {
            $row = $resultado->fetch();
            if ($row !== false) {
                $libro = new Libro($row);
            }
        }
        return $libro;
    }
    
}
