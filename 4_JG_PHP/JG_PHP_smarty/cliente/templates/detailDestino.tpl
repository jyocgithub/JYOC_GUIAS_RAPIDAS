<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <title>EJERCICIO REPASO SMARTYS</title>
      <link href="../css/listaDepartamentos.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="contenedor">
            <a href="listaBilletes.php">Volver</a>
            <h1>Relación de Destinos</h1>
             <form action="" method="POST">
                <table>
                    <tr>  
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>KM</th>
                        <th>PVP</th>                    
                        <th>Tren</th>
                        <th>Vagones</th>
                    </tr>
                    <tr>
                        <td>{$detailDestino->getCod()}</td>
                        <td>{$detailDestino->getNombre()}</td>
                        <td>{$detailDestino->getKm()}</td>
                        <td>{$detailDestino->getPvp()}</td>
                        <td>{$detailDestino->getTren()}</td>
                        <td>{$detailDestino->getVagones()}</td>                            
                    </tr>
                           
                </table>
            </form>
        </div>
    </body>
</html>