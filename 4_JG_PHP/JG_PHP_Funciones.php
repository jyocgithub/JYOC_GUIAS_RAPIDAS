<?php
function consolelog($mensaje)
{
    if (is_array($mensaje))
        $res = "<script>console.log( 'JYOC_DEBUG -----------> : " . implode(',', $mensaje) . "' );</script>";
    else
        $res = "<script>console.log( 'JYOC_DEBUG -----------> : " . $mensaje . "' );</script>";
    echo $res;
}


// escribe una variable, aun que sea un array, y un retorno de carro al final
function pln($a)
{
    print_r($a);
    echo "<br>";
}

function plnx($a)
{
    echo "<br> --->";
    print_r($a);
    echo "<br>";
}


function contains($origen, $busqueda){
    return strpos($origen, $busqueda) != false;
}


function startswith($origen, $busqueda){
    return substr($origen, 0, strlen($origen)) === $busqueda;
}

function endsWith( $origen, $busqueda ) {
    return substr( $origen, -  strlen( $busqueda ) ) === $busqueda;
}

// ejemplo de llamada
// descargaImagen('https://www.gstatic.com/webp/gallery3/1.png', 'flor.jpg', '.');

function descargaImagen($url, $nombreimagen, $directorio){
    $content = file_get_contents($url);
    file_put_contents($directorio . "/" . $nombreimagen, $content);
}




?>