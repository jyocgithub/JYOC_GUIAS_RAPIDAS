<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <title>EJERCICIO REPASO SMARTYS</title>
      <link href="../css/listaDepartamentos.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="contenedor">
            <h1>Relación de Billetes</h1>
             <form action="" method="POST">
                <table>
                    <tr>  
                        <th>Número</th>
                        <th>Cliente</th>
                        <th>Destino</th>
                        <th>Nombre</th>                    
                        <th>Vagones</th>
                        <th>PVP</th>
                        <th>Descuento</th> 
                        <th id="cabecera"></th>
                    </tr>
                    {foreach from=$listaBilletes item=billete}
                        <tr>
                            <td>{$billete->getNumero()}</td>
                            <td>{$billete->getCliente()}</td>
                            <td>{$billete->getDestino()}</td>
                            <td>{$billete->getNombre()}</td>
                            <td>{$billete->getVagon()}</td>
                            <td>{$billete->getPvp()}</td>
                            <td>{$billete->getDescuento()}</td>
                            <td id="dato"><a href="detailDestino.php?destino={$billete->getDestino()}">Detalle</a></td>                                
                        </tr>
                    {/foreach}   
                </table>
            </form>
        </div>
    </body>
</html>