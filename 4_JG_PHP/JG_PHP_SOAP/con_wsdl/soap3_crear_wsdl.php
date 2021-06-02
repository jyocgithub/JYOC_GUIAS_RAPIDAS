<?php

// _____________________ SERVER SOAP ___ llamando a funciones con una clase en el servidor y CREANDO WSDL 


// === CREAR WSDL
require_once("WSDLDocument.php");
require_once("soap3_servidor.php");
require_once("Clases.php");


$wsdl = new WSDLDocument(
    "Calcula3.php",
    "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl/soap3_servidor.php",
    "http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/con_wsdl"
);
echo $wsdl->saveXml();


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
