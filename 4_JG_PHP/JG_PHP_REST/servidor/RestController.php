<?php
/*
 * Fichero Controlador que decide que accion realizar segun lo qiue pida el cliente
 * Las posibles acciones a realizar y el mapeo entre la url de la peticion y las funciones aqui descriras estan en el fichero htaccess 
 */


require_once("factorialRestHandler.php");

$rh = new factorialRestHandler();

// si existe un parametro GET llamado "factor", hacemos la peticion de factorial
if(isset($_GET["factor"])){
    $rh->calcularFactorial($_GET["factor"]);
}


// si existe un parametro GET llamado "prim", hacemos la peticion de numero primo
if(isset($_GET["prim"])){
    $rh->verSiEsPrimo($_GET["prim"]);
}






