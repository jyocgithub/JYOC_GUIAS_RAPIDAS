<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>EJERCICIO REPASO SMARTYS</title>
    <link href="../css/listaDepartamentos.css" rel="stylesheet" type="text/css">
</head>

<body>
    <div class="contenedor">
        <h1>Relación de LIBROS</h1>
        <form action="" method="POST">
            <table>
                <tr>
                    <th>Codigo</th>
                    <th>Titulo</th>
                    <th>Autor</th>
                    <th>Editorial</th>
                    <th>Precio</th>
                    <th id="cabecera"></th>
                </tr>
                {foreach from=$listalibros item=cadalibro}
                <tr>
                    <td>{$cadalibro->getCodigo()}</td>
                    <td>{$cadalibro->getTitulo()}</td>
                    <td>{$cadalibro->getAutor()}</td>
                    <td>{$cadalibro->getEditorial()}</td>
                    <td>{$cadalibro->getPrecio()}</td>
                    <td id="dato"><a href="detailDestino.php?destino={$billete->getDestino()}">Nacionalidad</a></td>
                </tr>
                {/foreach}
            </table>
        </form>
    </div>
</body>

</html>