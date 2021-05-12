<?php
/*
 *Fichero Controlador de la acción que recibe el servidor desde la petición que lanza el cliente
 *En el fichero htaccess se definen tres reglas.
 *En función de ello, se accederá a una opción u otra para recuperar la información de un método u otro
 */


//Se llama al fichero manjeador de los Vehículos
require_once("billeteRestHandler.php");

$rh = new billeteRestHandler();

$rh->getDescuento($_GET["cod"]);



