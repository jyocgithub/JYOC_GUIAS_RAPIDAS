<?php
    //Se incluyen los ficheros neecsarios
    require_once('../servidor/DBJYOC.php');
    require_once('../servidor/Libro.php');
    require_once('smarty/libs/Smarty.class.php');
    // include_once("JG_PHP_Funciones.php");
    
    //Se instancia un objeto Smarty y se realizan ls configuraciones necesarias para que funcione el POO-Smarty
    $smarty = new Smarty;

    // $smarty->force_compile = true;
    $smarty->debugging = true;
    $smarty->cache_lifetime = 120;
    // $smarty->caching = true;
    
    // $smarty->cache_dir = 'cache';
    $smarty->template_dir = 'templates';
    $smarty->config_dir = 'config';
    // $smarty->compile_dir = 'templates_c';
    $smarty->setCompileDir('templates_c');
    
    //Se recuperan los vehículos de la base de datos
    $bd = new DBJYOC();
    $todosloslibros =  $bd->getLibros();
    
    //Se crea una variable en la que se guarde el listado de vehículos
    $smarty->assign('listalibros', $todosloslibros);

    //Se crean otras variables para otros elementos
    $smarty->assign("nombre", "Ernesto Matensalsa", true);
    $smarty->assign("mensajecorto", "Todos en fila, por favor", true);
    
    //Se manda la información a la plantilla que ejecuta el código html
    $smarty->display('templates/listaLibrosSimple.tpl');

