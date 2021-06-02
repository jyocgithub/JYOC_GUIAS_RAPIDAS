<?php
// Para que el navegador no haga cache de los datos devueltos por la página PHP.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
header("Content-type: text/xml;charset=utf-8");

sleep(2);  // para que la peticion tarde algo en llegar

$nomProvinciaPropuesta = $_GET['nomProvincia'];
echo $nomProvinciaPropuesta . ": ";

$provincias = array("Guipuzcoa", "Vizcaya", "Alava");

if (in_array($nomProvinciaPropuesta, $provincias)) {
    echo "SI! esa es una de las provincias vascas";
}else{
    echo "NO... esa no es una provincia vasca! ";
}

?> 