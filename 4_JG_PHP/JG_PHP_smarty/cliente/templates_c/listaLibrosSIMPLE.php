<?php
    //Se incluyen los ficheros neecsarios
    require_once('../include/DBJYOC.php');
    require_once('../include/Libro.php');
    require_once('../smarty/libs/Smarty.class.php');
    include_once("JYOCPHP_Funciones.php");
    
    //Se instancia un objeto Smarty y se realizan ls configuraciones necesarias para que funcione el POO-Smarty
    $smarty = new Smarty;
    $smarty->template_dir = '../templates';
    $smarty->config_dir = '../config';
    $smarty->cache_dir = '../cache';
    $smarty->compile_dir = '../templates_c';
    
    //Se recuperan los vehículos de la base de datos
    $bd = new DBJYOC();
    $bd->getLibros();
    consolelog("trasleer");
    $todosloslibros =  $bd->getLibros();
        
    //Se crea una variable en la que se guarde el listado de vehículos
    $smarty->assign('listalibros', $todosloslibros);
    
    //Se manda la informaión a la plantilla que ejecuta el código html
    $smarty->display('listaLibrosSimple.tpl');

