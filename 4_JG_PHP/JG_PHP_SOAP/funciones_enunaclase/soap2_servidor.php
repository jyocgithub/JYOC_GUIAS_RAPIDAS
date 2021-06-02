<?php

// _____________________ SERVER SOAP ___ llamando a funciones de UNA CLASE en el servidor

$uri="http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_enunaclase/"; 

$server = new SoapServer(null,array('uri'=>$uri)); 

$server->setClass('Calcula');
$server->handle();


class Calcula {

   
    public function suma2($a, $b){ 
        return $a+$b; 
    }

    public function resta2($a, $b){ 
        return $a-$b; 
    }
}

