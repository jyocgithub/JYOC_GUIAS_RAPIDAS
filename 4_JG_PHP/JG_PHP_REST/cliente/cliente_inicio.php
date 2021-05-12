<?php


$mensajeRespuesta = "";
if (isset($_POST["calcular"])) {
    $respuesta = @file_get_contents('http://localhost/workspace_htdocs/GUIAJYOCPHP_REST/servidor/factorial?factor=' . $_POST["factorial"]);
    // var_dump($http_response_header);  // contiene un array con los resultados de la peticioon de file_get_contents, con esta estructura: 
    // array(7) { 
        //     [0]=> string(15) "HTTP/1.1 200 OK" 
        //     [1]=> string(35) "Date: Mon, 15 Mar 2021 10:52:44 GMT" 
        //     [2]=> string(86) "Server: Apache/2.4.41 (Unix) OpenSSL/1.1.1d PHP/7.2.25 mod_perl/2.0.8-dev Perl/v5.16.3" 
        //     [3]=> string(24) "X-Powered-By: PHP/7.2.25" 
        //     [4]=> string(17) "Content-Length: 2" 
        //     [5]=> string(17) "Connection: close" 
        //     [6]=> string(30) "Content-Type: application/json" } 
        // aqui podemos ver el response code (200 o 404 o lo que sea). Se puedde extraer el codsigo con la funcoin que se muestra mas abajo 

    if ($respuesta !== false) {
        // Como la respuesta es un simple numero, no hace falta ni decodificarla ni convertirla en nada
        // Si la respuesta fuera algo mas complejo, deberiamos mejor haber creado un json a partir de un objeto, no un simple int o string
        // $respuesta_sinformato = json_decode($respuesta);  
        $respuesta_sinformato = $respuesta;
        
        if ($respuesta_sinformato === 'ERRORFACTORIAL') {
            $mensajeRespuesta = " No se pudo calcular el facorial de " .  $_POST["factorial"] . " , debe ser un numero mayor de 0";
        } else {
            $mensajeRespuesta = "EL factorial de " .  $_POST["factorial"] . " es " .  $respuesta_sinformato;
        }
    } else {
        echo "Error en la respuesta REST de peticion de factorial";
    }
}

if (isset($_POST["primo"])) {
    $respuesta = @file_get_contents('http://localhost/workspace_htdocs/GUIAJYOCPHP_REST/servidor/primo?prim=' . $_POST["factorial"]);
    // var_dump($http_response_header);
    if ($respuesta !== false) {
        // Si no la "decodificamos", la respuesta viene con comillas al ser un simple string. 
        // Si la respuesta fuera algo mas complejo, deberiamos mejor haber creado un json a partir de un objeto, no un simple int o string
        $respuesta_sinformato = json_decode($respuesta); 

        if ($respuesta_sinformato === 'ERRORPRIMO') {
            $mensajeRespuesta = " No se puedo mirar si es primo " .  $_POST["factorial"] . " , debe ser un numero mayor de 0";
        } else {
            $mensajeRespuesta = "EL numero " .  $_POST["factorial"] . " " .   $respuesta_sinformato;
        }
    } else {
        echo "Error en la respuesta REST de peticion de numero primo";
    }
}



function getHttpCode($http_response_header)
{
    if(is_array($http_response_header))
    {
        $parts=explode(' ',$http_response_header[0]);
        if(count($parts)>1) //HTTP/1.0 <code> <text>
            return intval($parts[1]); // saca la segunda palabra, el codigo real
    }
    return 0;
}
?>



<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Factorial o Primo con REST</title>
    <link href="css/estilo.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="contenedor">
        <div class="encabezado">
            <h1>Factorial o Primo con REST </h1>
        </div>
        <form action="" id="" name="" method="POST">
            <div class="formulario">
                <label for="factorial">Indique un numero</label>
                <input id="factorial" name="factorial" type="text" /><br><br>
                <button type="submit" id="calcular" name="calcular">Calcular Factorial</button>
                <button type="submit" id="calcular" name="primo">Calcular si es primo</button>
                <div id="respu">
                    <?php
                        // if ($mensajeRespuesta !== "") {
                            echo $mensajeRespuesta;
                        // }
                    ?>
                </div>
            </div>
        </form>
    </div>
</body>

</html>



