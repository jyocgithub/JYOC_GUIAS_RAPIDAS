<?php

include_once("claseLibro.php");

$ip = 'localhost';
$basededatos = 'JYOC_PEDIDOS';
$usuario = 'root';
$password = '';
$opciones = [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_CASE => PDO::CASE_NATURAL
];
$codificacion = "SET CHARACTER SET UTF8";
try {
    $conexion = new PDO("mysql:host=$ip; dbname=$basededatos", $usuario, $password, $opciones);
    $conexion->exec($codificacion);
} catch (Exception $ex) {
    die('Conexion fallida a la bbdd: ' . $ex->getMessage());
}



$sql = 'SELECT * FROM LIBROS';
$arrayLibros = [];
$grupo = $conexion->query($sql);
if ($grupo->rowCount() > 0) {
    foreach ($grupo as $fila) {
        $lib = new Libro( $fila['Codigo_de_libro'],$fila['Titulo'],$fila['Autor'],$fila['Editorial'],$fila['Precio']);
        $arrayLibros [] = $lib;
    }
}
echo json_encode($arrayLibros);

?>
