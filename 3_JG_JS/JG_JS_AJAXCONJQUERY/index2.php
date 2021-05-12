<?php


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



$sql = 'SELECT autor, titulo FROM libros';
$grupo = $conexion->query($sql);
if ($grupo->rowCount() > 0) {
    foreach ($grupo as $fila) {
        print $fila['autor'] . " - ";
        print $fila['titulo'] . "<br>";
    }
}

?>
