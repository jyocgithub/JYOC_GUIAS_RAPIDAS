<?php

// _____________________ SERVER SOAP ___ llamando a funciones en el servidor

function suma($a,$b){ 
    return $a+$b; 
} 


function resta($a,$b){ 
    return $a-$b; 
}

$uri="http://localhost/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_SOAP/funciones_sueltas/"; 

$server = new SoapServer(null,array('uri'=>$uri)); 

$server->addFunction("suma"); 
$server->addFunction("resta"); 
$server->handle();

