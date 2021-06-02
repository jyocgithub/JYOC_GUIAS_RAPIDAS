// =========== EJECUTAR FUNCION TRAS CARGA DEL DOM =========

// ==== VERSION NO CROSS-BROWSER, version 1
window.onload = function() {
    iniciar();
}

function iniciar() {
    // Cargamos de forma asíncrona lo que devuelve la página procesar.php
    // En este caso sólo ponemos los parámetros que pasaremos a la página
    llamadaAjaxAsincrona("nombre=Laura&apellidos=Folgado");
}


// Carga el contenido de la url de forma asíncrona con Ajax
function llamadaAjaxAsincrona(parametros) {
    // Creamos un objeto XHR -- VERSION 1, sin importarnos que navegador usamos
    miXHR = new XMLHttpRequest();
    // Creamos un objeto XHR -- VERSION 2, controlando si es IE u otro navegador
    // miXHR = new obtenerObjetoXHR();
    if (miXHR) {
        // Carga el indicador Ajax antes de realizar la petición.
        document.getElementById("indicador").innerHTML = "<img src='ajax_loader.gif'/>";
        //Si existe el objeto miXHR abrimos la url (asíncrona)
        miXHR.open("POST", "procesar.php", true);
        // En cada cambio de estado llama a estadoPetición
        miXHR.onreadystatechange = estadoPeticion;
        miXHR.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // Hacemos la petición al servidor con GET y parámetro null
        miXHR.send(parametros);
    }
}


// Se llama en cada cambio de estado de la petición.
// 0 No inicializado(el método open no a sido llamado)
// 1 Cargando(se llamó al método open)
// 2 Cargado(se llamó al método send y ya tenemos la cabecera de la petición HTTP y el status)
// 3 Interactivo(la propiedad responseText tiene datos parciales)
// 4 Completado(la propiedad responseText tiene todos los datos pedidos al servidor)
// Los valores mas habituales de respuesta en el código Status son : 200 encontrado   y 404 no encontado
function estadoPeticion() {
    if (this.readyState == 4 && this.status == 200) {
        // recogemos la respuesta de la petición AJAX en una constante, por su queremos luego controlarla
        const respuestaAJAX = this.responseText;

        // Quitamos el gif de "cargando AJAX".
        document.getElementById("indicador").innerHTML = "";

        // Metemos la respuesta en la caja de resultados
        const nodoResultados = document.getElementById("resultados");
        borrarContenidoDeNodo(nodoResultados);
        agregarTextoAUnNodo(nodoResultados, respuestaAJAX);
    }

}