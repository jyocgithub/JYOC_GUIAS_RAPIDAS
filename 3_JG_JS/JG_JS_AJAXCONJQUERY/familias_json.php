<?php
// // Para que el navegador no haga cache (fecha de expiración menor a la actual)
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
// // Indicamos  al navegador que va a recibir contenido JSON
header("Content-Type: application/json");


class Familia
{
    public $id;
    public $nombreFamilia;  
    public $foto;  

    public function Familia($id, $nombreFamilia, $foto)
    {
        $this->id = $id;
        $this->nombreFamilia = $nombreFamilia;
        $this->foto = $foto;
    }
}


function leerTodasLasFamilias(){

    include_once("connectBD.php");
    $conexion = conectar();
    $sql = 'SELECT id, nombreFamilia,foto FROM familias';
    $grupo = $conexion->query($sql);
    
    $familias= array();
    if ($grupo->rowCount() > 0) {
        foreach ($grupo as $fila) {
            $id = $fila['id'];
            $nombreFamilia = $fila['nombreFamilia'];
            $foto = base64_encode($fila['foto']);
       
            $l = new Familia($id,$nombreFamilia, $foto);
            array_push($familias, $l);
        }
    }
    
    echo json_encode($familias);
    return;
}

 leerTodasLasFamilias();   
    
    