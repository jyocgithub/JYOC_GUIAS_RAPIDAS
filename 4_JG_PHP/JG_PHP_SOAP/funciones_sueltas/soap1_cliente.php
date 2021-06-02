<?php

// _____________________ CLIENTE SOAP ___ llamando a funciones en el servidor

$url = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_sueltas/soap1_servidor.php";
$uri = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_sueltas/"; // si no se usa WSDL se necesita el parametro uri

$parametrosSoap = array(
    'location' => $url, 
    'uri' => $uri,
    'encoding' => 'UTF-8',
    'verifypeer' => false,
    'verifyhost' => false,
    'soap_version' => SOAP_1_2,
    'trace' => 1,
    'exceptions' => 1,
    'connection_timeout' => 180
);

$cliente = new SoapClient(null, $parametrosSoap);

try {
    $suma = $cliente->suma(2, 3);
    $resta = $cliente->resta(2, 3);
    print("La suma es " . $suma);
    print("<br />La resta es " . $resta);
} catch (SOAPFault $e) {
    echo $e->getMessage() . PHP_EOL;
}
