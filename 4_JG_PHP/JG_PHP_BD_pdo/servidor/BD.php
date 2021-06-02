<?php

$conexion;
$bbdd = "JYOC_LIBROS";
$usuario = "root";
$password = "root";


function conectar()
{
    global $conexion, $bbdd, $usuario, $password;
    try {
        $conexion = new PDO("mysql:host=127.0.0.1; dbname=$bbdd; charset=UTF8", $usuario, $password);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } catch (PDOException $e) {
        echo 'Error: ' . $e->getMessage();
    }
}

function desconectar()
{
    global $conexion;
    try {
        $conexion = null;
        unset($conexion);
    } catch (PDOException $ex) {
        echo "Error al desconectar a la base de datos" . $ex->getMessage();
    }
}

// Usando un metodo generico de consultas simples
function selectSimple($instruccionSelectSql)
{
    $registros = [];
    try {
        conectar();
        global $conexion;
        $registros = $conexion->query($instruccionSelectSql);
    } catch (PDOException $e) {
        echo 'Error: ' . $e->getMessage();
    }
    desconectar();
    return $registros;
}




function registrarUsuario($email, $apellidos, $nombre, $sexo, $pass, $peso, $altura)
{
    try {
        conectar();
        global $conexion;
        echo "intento guardar";
        $instruccion = $conexion->prepare("INSERT INTO usuarios VALUES(?,?,?,?,?,?,?,0,0)");
        $instruccion->bindParam(1, $email);
        $instruccion->bindParam(2, $apellidos);
        $instruccion->bindParam(3, $nombre);
        $instruccion->bindParam(4, $sexo);
        $instruccion->bindParam(5, $pass);
        $instruccion->bindParam(6, $peso);
        $instruccion->bindParam(7, $altura);

        $instruccion->execute();
        echo "guardado";
    } catch (PDOException $e) {
        echo 'Error: ' . $e->getMessage();
    }
}



// Usando un metodo de consultas para cada tabla (peor)
function consultarAlimentos()
{
    $registros = [];
    try {
        conectar();
        global $conexion;
        $registros = $conexion->query("SELECT * FROM alimento");
    } catch (PDOException $e) {
        echo 'Error: ' . $e->getMessage();
    }
    desconectar();
    return $registros;
}

function consultarIngesta()
{ 
        $registros = [];
        try {
            conectar();
            global $conexion;
            $registros = $conexion->query("SELECT * FROM ingesta");
        } catch (PDOException $e) {
            echo 'Error: ' . $e->getMessage();
        }
        desconectar();
        return $registros;
    
}



// Usando un metodo generico de consultas de un datro simple detipo entero
function buscarCampoInteger($tabla, $valornecesario, $campoWhere, $valorWhere)
{
    $resultado = "";
    try {
        conectar();
        global $conexion;
        $registros = $conexion->query("select $valornecesario from $tabla where $campoWhere= $valorWhere");
        $resultado = $registros->fetch(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
        echo 'Error: ' . $e->getMessage();
    }
    desconectar();
    return $resultado[$valornecesario];
}
