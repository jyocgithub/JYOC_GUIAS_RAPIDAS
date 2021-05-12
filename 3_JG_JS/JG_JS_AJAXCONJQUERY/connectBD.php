<?php

// $opciones = array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8");





function conectar()
{
    $ip = 'localhost:3306';
    $basededatos = 'almacen';
    $usuario = 'root';
    $password = '';
    $opciones = [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_CASE => PDO::CASE_NATURAL
    ];
    $codificacion = "SET CHARACTER SET UTF8";
    try {
        $conexion = new PDO("mysql:host=$ip; dbname=$basededatos", $usuario, $password, $opciones);
        // $conexion->exec($codificacion);
        return $conexion;
    } catch (Exception $ex) {
        die('Conexion fallida a la bbdd: ' . $ex->getMessage());
    }
    return null;
} 








// // Create connection
// $conn = mysqli_connect($servername, $username, $password, $database);
// // Check connection
// if (!$conn) {
//     die("Connection failed: " . mysqli_connect_error());
// }
// echo "Connected successfully";
// mysqli_close($conn);
// 
