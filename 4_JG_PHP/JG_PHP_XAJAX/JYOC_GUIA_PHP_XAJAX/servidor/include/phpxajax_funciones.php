<?php

require_once('libsxajax/xajax_core/xajax.inc.php');
require_once('DB.php');
require_once('libro.php');

$xajax = new xajax();
$xajax->configure('javascript URI', './include/libs/');
$xajax->register(XAJAX_FUNCTION, "xajax_leerUnlibro");
$xajax->register(XAJAX_FUNCTION, "xajax_leerlibros");
$xajax->processRequest();

function xajax_leerUnlibro($codpedido)
{
    $resp = new xajaxResponse();
    $db = new DB();
    $libro = $db->getLibroPorCodido($codpedido);
    if (isset($libro)) {
        $contenidoscript  =  "document.getElementById('idrespuesta').innerHTML =  '"  . $libro->getTitulo()  .   "'";
    } else {
        $contenidoscript  =  "document.getElementById('idrespuesta').innerHTML =  'No existe tal libro'";
    }
    $resp->script($contenidoscript);
    return $resp;
}

function xajax_leerlibros()
{
    $resp = new xajaxResponse();
    $db = new DB();
    $libros = $db->getLibros();
    if (isset($libros)) {
        $cont = crearTablaHtml($libros);
        $contenidoscript  =  "document.getElementById('idtabla').innerHTML =  ' "   . $cont   .    "'";
    } else {
        $contenidoscript  =  "document.getElementById('idtabla').innerHTML =  'No se encuentran libros...'";
    }
    $resp->script($contenidoscript);
    return $resp;
}

function crearTablaHtml($libros)
{
    $cont =  "<table>";
    $cont .=  "<tr>";
    $cont .= "<th>Código Libro</th>";
    $cont .= "<th>Titulo</th>";
    $cont .= "<th>Autor</th>";
    $cont .= "<th>Editorial</th>";
    $cont .= "<th>Precio</th>";
    $cont .= "</tr>";

    foreach ($libros as $libro) {
        $cont .= '<tr>';

        $cont .= '<td>';
        $cont .= $libro->getCodigo();
        $cont .= '</td>';
        $cont .= '<td>';
        $cont .= $libro->getTitulo();
        $cont .= '</td>';
        $cont .= '<td>';
        $cont .= $libro->getAutor();
        $cont .= '</td>';
        $cont .= '<td>';
        $cont .= $libro->getEditorial();
        $cont .= '</td>';
        $cont .= '<td>';
        $cont .= $libro->getPrecio();
        $cont .= '</td>';

        $cont .= '</tr>';
    }
    $cont .= '  </table>  ';
    return $cont;
}
