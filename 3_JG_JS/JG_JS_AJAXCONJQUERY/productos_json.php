<?php
// // Para que el navegador no haga cache (fecha de expiración menor a la actual)
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');
// // Indicamos  al navegador que va a recibir contenido JSON
header("Content-Type: application/json");

include_once("connectBD.php");

class Producto
{
    public $id;
    public $idFamilia;
    public $descripcion;
    public $precioCoste;
    public $precioVenta;
    public $stock;
    public $stockMin;
    public $codProv;
    public $unidadesVendidas;
    public $foto;
    public $fecha;

    public function Producto($p1, $p2, $p3, $p4, $p5, $p6, $p7, $p8, $p9, $p10, $p11)
    {
        $this->id = $p1;
        $this->idFamilia = $p2;
        $this->descripcion = $p3;
        $this->precioCoste = $p4;
        $this->precioVenta = $p5;
        $this->stock = $p6;
        $this->stockMin = $p7;
        $this->codProv = $p8;
        $this->unidadesVendidas = $p9;
        $this->foto = $p10;
        $this->fecha = $p11;
    }
}


function buscarIdFamilia($nombrebuscado)
{
    $conexion = conectar();

    $sql = "SELECT id FROM familias where nombreFamilia = '$nombrebuscado'";
    $grupo = $conexion->query($sql);

    if ($grupo->rowCount() > 0) {
        $fila = $grupo->fetch(PDO::FETCH_ASSOC);
        // foreach ($grupo as $fila) {
            //     $id = $fila['id'];
            //     $nombreFamilia = $fila['nombreFamilia'];
            //     $l = new Familia($id, $nombreFamilia);
            //     array_push($familias, $l);
            // }
            return $fila['id'];
        }
    }
    
    
    function leerTodosLosProductos($nombrebuscado)
    {
        $idencontrado = buscarIdFamilia($nombrebuscado);
        
        $sql = "SELECT id,idFamilia,descripcion,precioCoste,precioVenta,stock,stockMin,codProv ,unidadesVendidas,foto,fecha FROM articulos";
        if($nombrebuscado != "Todos los Productos"){
            $sql .= "  where idFamilia = '$idencontrado' and stock > stockMin";
        }else{
            $sql .= "  where stock > stockMin";

        }
        $conexion = conectar();
        $grupo = $conexion->query($sql);
        $articulos = array();
        if ($grupo->rowCount() > 0) {
            foreach ($grupo as $fila) {
                $id = $fila['id'];
                $idFamilia = $fila['idFamilia'];
                $descripcion = $fila['descripcion'];
                $precioCoste = $fila['precioCoste'];
                $precioVenta = $fila['precioVenta'];
                $stock = $fila['stock'];
                $stockMin = $fila['stockMin'];
                $codProv = $fila['codProv'];
                $unidadesVendidas = $fila['unidadesVendidas'];
                $foto = base64_encode($fila['foto']);
                $fecha = $fila['fecha'];
                $l = new Producto($id, $idFamilia, $descripcion, $precioCoste, $precioVenta, $stock, $stockMin, $codProv, $unidadesVendidas, $foto, $fecha);
                array_push($articulos, $l);
            }
        }
        
        $todo = json_encode($articulos);
        echo $todo;
        return;
    }
    
    
    

$nombrebuscado = $_POST['nombrebuscado'];

leerTodosLosProductos($nombrebuscado);



