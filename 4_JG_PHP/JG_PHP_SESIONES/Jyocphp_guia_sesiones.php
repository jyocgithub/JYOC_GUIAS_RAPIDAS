<?php

// arrancamos la sesion si no esta ya arrancada
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

// cerrar sesion 
session_write_close();

// crear y leer elementos en la sesion
$_SESSION['clave_del_dia'] = "JYOCForever";
if (!isset($_SESSION['numero_accesos'])) {
    $_SESSION['numero_accesos'] = 1;
}else{
    $_SESSION['numero_accesos']++;
}

print_r($_SESSION['numero_accesos'] );

// borrar un elemento de la sesion 
unset($_SESSION['numero_accesos']);
