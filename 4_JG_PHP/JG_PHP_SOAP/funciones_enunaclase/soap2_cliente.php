<?php

// _____________________ CLIENTE SOAP ___ llamando a funciones de UNA CLASE en el servidor

$url = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_enunaclase/soap2_servidor.php";
$uri = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_enunaclase";

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
    $suma = $cliente->suma2(2, 3);
    $resta = $cliente->resta2(2, 3);
    print("La suma es " . $suma);
    print("<br />La resta es " . $resta);
} catch (SOAPFault $e) {
    echo $e->getMessage() . PHP_EOL;
}
