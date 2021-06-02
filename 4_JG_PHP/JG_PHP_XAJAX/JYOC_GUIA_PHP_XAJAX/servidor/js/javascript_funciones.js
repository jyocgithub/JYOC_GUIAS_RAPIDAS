window.onload = init;

function init() {
    document.getElementById('consultar').addEventListener('click', consultaLibro);
    document.getElementById('listar').addEventListener('click', listarLibros);
}

function consultaLibro() {
    var codpedido = document.getElementById('codigopedido').value;
    // se envia una peticion AJAX con esta instruccion, los parámetros se separan por comas en el array de parameters
    xajax.request({ xjxfun: "xajax_leerUnlibro" }, { mode: 'synchronous', parameters: [codpedido] });
}

function listarLibros() {
    xajax.request({ xjxfun: "xajax_leerlibros" }, { mode: 'synchronous', parameters: [] });
}