<?php

// _____________________ CLIENTE SOAP ___ llamando a funciones con una clase en el servidor y CREANDO WSDL 

$url = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/soap3_servidor.php";
$uri = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/";

$parametrosSoap = array(
    'location' => $url, 
    'uri' => $uri,
    'encoding' => 'UTF-8',
    'verifypeer' => false,
    'verifyhost' => false,
    'soap_version' => SOAP_1_2,
    'trace' => 1,
    'exceptions' => 1,
    'connection_timeout' => 180,
    'ssl' => array(
        'ciphers' => 'RC4-SHA',
        'verify_peer' => false,
        'verify_peer_name' => false
        )
    );
    
$urlwsdl = "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/Calcula3.wsdl?WSDL";

$cliente = new SoapClient($urlwsdl, $parametrosSoap);

try {
    $suma = $cliente->suma3(2, 3);
    $resta = $cliente->resta3(2, 3);
    print("La suma es " . $suma);
    print("<br />La resta es " . $resta);
} catch (SOAPFault $e) {
    echo $e->getMessage() . PHP_EOL;
}
