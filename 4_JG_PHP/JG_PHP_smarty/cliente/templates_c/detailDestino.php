<!--
    Curso: 2020/2021
    Asignatura: DWES
    Unidad de Trabajo 5 - DWES05 Ejercicio 2
    Autor: Borja Sainz Ansoleaga

    Fichero que maneja la conexión con la clase Smarty para trabajar con plantillas y poder mostrar el detalle de la furgoneta
-->

<?php
    //Se incluyen los ficheros neecsarios
    require_once('../include/DB.php');
    require_once('../include/destino.php');
    require_once('../smarty/libs/Smarty.class.php');
    
    //Se instancia un objeto Smarty y se realizan ls configuraciones necesarias para que funcione el POO-Smarty
    $smarty = new Smarty;
    $smarty->template_dir = '../templates';
    $smarty->config_dir = '../config';
    $smarty->cache_dir = '../cache';
    $smarty->compile_dir = '../templates_c';
    
    //Se recuperan los datos de la furgoneta de la base de datos
    $destino = DB::getDestino($_GET["destino"]);
    
    
    
    //Se crea una variable en la que se guarde el listado de furgonetas
    $smarty->assign('detailDestino', $destino);
    
    //Se manda la informaión a la plantilla que ejecuta el código html
    $smarty->display('detailDestino.tpl');

