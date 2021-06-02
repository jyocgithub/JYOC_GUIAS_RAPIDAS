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





function testSelectSimple()
{
    global $conexion, $bbdd, $usuario, $password;
    try {
        $conexion = new PDO("mysql:host=127.0.0.1; dbname=$bbdd; charset=UTF8", $usuario, $password);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } catch (PDOException $e) {
        echo 'Error al conectar a bbdd: ' . $e->getMessage();
    }
    $cursor = $conexion->prepare("SELECT * from LIBRO");
    $cursor->execute();

    if (isset($cursor)) {
        if ($cursor->rowCount() > 0) {
            while ($fila = $cursor->fetch(PDO::FETCH_ASSOC, PDO::FETCH_ORI_NEXT)) {
                // print_r($fila);
                foreach ($fila as $clave => $valor) {
                    echo "Clave : " . $clave . "  -- Valor: " . $valor . '<br>';
                }
                echo "------------------------ " . '<br>';
            }
        } else {
            echo "No hay registros con esa condicion";
        }
    } else {
        echo "Consulta erronea";
    }

    desconectar();
    return $cursor;
}
testSelectSimple();
