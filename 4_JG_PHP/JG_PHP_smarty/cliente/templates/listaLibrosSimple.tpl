{include file="header.tpl" title="Ejercicio JYOCSmarty"}
    Biblioteca de {$nombre}
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
                </tr>
                {/foreach}
            </table>
        </form>
    </div>

    ==============  otros ejemplos de uso de smarty
    <br>
    Fecha y hora actuales:  {$smarty.now|date_format:"%Y-%m-%d %H:%M:%S"} 
    <br>
    La variable de entorno SERVER_NAME es : {$smarty.server.SERVER_NAME}
    <br>
    La variable de usuario $mensajecorto es : {$mensajecorto}
    <br>
    La variable de usuario $mensajecorto toda en mayusculas es  {$mensajecorto|upper}
    <br>
    La variable de usuario $mensajecorto "capitalizada" es  {$mensajecorto|capitalize}
    <br>
    Mas ejemplos en la carpeta smarty/demo/indexphp  y   smarty/demo/templates


{include file="footer.tpl"}