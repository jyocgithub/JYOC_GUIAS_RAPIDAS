<?php
    //Se incluyen los ficheros neecsarios
    require_once('../include/DBJYOC.php');
    require_once('../include/Libro.php');
    require_once('../smarty/libs/Smarty.class.php');
    
    //Se instancia un objeto Smarty y se realizan ls configuraciones necesarias para que funcione el POO-Smarty
    $smarty = new Smarty;
    $smarty->template_dir = '../templates';
    $smarty->config_dir = '../config';
    $smarty->cache_dir = '../cache';
    $smarty->compile_dir = '../templates_c';
    
    //Se recuperan los vehículos de la base de datos
    $todosloslibros = DB::getLibros();
    
    for($i=0; $i < count($todosloslibros); $i++){
        $get_desc=@file_get_contents('http://localhost/Pacheco_DWES_2ev/Examen_19_20/smarty_rest/servidor/descuento?cod=' . $billetes[$i]->getNumero());
        if ($get_desc !== false) {
            $desuen = json_decode($get_desc);
            $billetes[$i]->setDescuento($desuen);
            //echo $desuen;
        }else{
            echo "Error en la captura REST";
        }
    }
    
    $billetes_fin = $billetes;
    
    //Se crea una variable en la que se guarde el listado de vehículos
    $smarty->assign('listaBilletes', $billetes_fin);
    
    //Se manda la informaión a la plantilla que ejecuta el código html
    $smarty->display('listaBilletes.tpl');

