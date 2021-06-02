<?php
require_once("include/phpxajax_funciones.php");
require_once("include/DB.php");
require_once("include/libro.php");
require_once("include/JYOCPHP_Funciones.php");

$db = new DB();
$libros = $db->getLibros();
?>
<!doctype html>
<html lang="es">

<head>
    <title>Ejemplo Xajax , JS y PHP</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link href="css/estilo.css" rel="stylesheet" type="text/css" />
    <script src="js/javascript_funciones.js" type="text/javascript"></script>

    <?php $xajax->printJavascript(); ?>

</head>

<body>
    <div class="contenedor">
        <form action="" id="myForm">
            <h1>LISTAR</h1>
            <button type='button' class='centrado' id='listar' >Listar</button>
            <div id='idtabla'></div>

            <h1>CONSULTAR</h1>
            <form class='centrado' >
            Codigo solicitado<input name="codpedido" value="" id="codigopedido">
            <button type='button' class='centrado' id='consultar' >Consultar</button>
            <div id='idrespuesta'></div>
            </form>
        </form>
    </div>
</body>
</html>