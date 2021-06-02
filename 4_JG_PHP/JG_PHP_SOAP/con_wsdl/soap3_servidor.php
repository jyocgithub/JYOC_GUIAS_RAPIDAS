<?php

// _____________________ SERVER SOAP ___ llamando a funciones con una clase en el servidor y CREANDO WSDL 
require_once("WSDLDocument.php");

$wsdl = new WSDLDocument(
    "Calcula3",
    "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/soap3_servidor.php",
    "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/"
);
echo $wsdl->saveXml();


// === PROVEER SERVICIO PUBLICAMENTE
$server = new SoapServer("http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/Calcula3.wsdl");
$server = new SoapServer("http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/Calcula3.wsdl");
// $server = new SoapServer(null, array('uri'=>'')); 

// $server = new SoapServer("Calcula3");
$server->setClass('Calcula3');
$server->handle();

class Calcula3
{
    /** 
     * Suma dos números y devuelve el resultado 
     * @param float $a 
     * @param float $b 
     * @return float */
    public function suma3($a, $b)
    {
        return "hola";
        // return $a + $b;
    }

    /** 
     * Resta dos números y devuelve el resultado 
     * @param float $a 
     * @param float $b 
     * @return float */
    public function resta3($a, $b)
    {
        return $a - $b;
    }
}
