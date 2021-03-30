// *******************************************************************
// *******************************************************************
// ******                                                       ******
// ******               UTILIDADES JAVASCRIPT                   ******
// ******                                                       ******
// *******************************************************************
// *******************************************************************

//  +-------------------------------------------------+
//  |   EJECUTAR FUNCION TRAS CARGA DEL DOM           |
//  |                                                 |
//  |   Ejecuta una funcion cuando se ha cargado      |
//  |   completamente el arbol DOM de elementos       |
//  +-------------------------------------------------+

// ==== VERSION NO CROSS-BROWSER, version 1
// window.onload = function() {
//     iniciar();
// }

// ==== VERSION NO CROSS-BROWSER, version 2
// addEventListener("load", iniciar, false);

// ==== VERSION NO CROSS-BROWSER, version 3
// window.onload = iniciar;

// ==== VERSION CROSS-BROWSER
// -- Una función "cross-browser" quiere decir que esta preparada para que funcione todo 
// -- y se vea exactamente igual en cualquier navegador
// -- Ademas, esta funcion es una Self - Invoking Function o Funciones Auto - invocadas,
// -- que son Funciones como expresión, que se ejecutan sin que nadie las haya llamado.
// -- Para ello, se debe añadir dos paréntesis(), al final de la función.
// var crearEvento = (function() {
//     function w3c_crearEvento(elemento, evento, mifuncion) {
//         elemento.addEventListener(evento, mifuncion, false);
//     }

//     function ie_crearEvento(elemento, evento, mifuncion) {
//         var fx = function() {
//             mifuncion.call(elemento);
//         };
//         //Enlazamos el objeto con attachEvent para IE
//         elemento.attachEvent("on" + evento, fx);
//     }
//     if (typeof window.addEventListener !== "undefined") {
//         return w3c_crearEvento;
//     } else if (typeof window.attachEvent !== "undefined") {
//         return ie_crearEvento;
//     }
// })(); // <= Esta es la parte más crítica - tiene que terminar en ()
// -- Llamada a la funcion cross - browser que crea un evento
// -- En este caso, cuando se carga la pagina llama a la funcion iniciar()
// -- debe estar despues de la funcion...
// crearEvento(window, "load", iniciar);


//  +-------------------------------------------------+
//  |   agregarTextoAUnNodo                           |
//  |                                                 |
//  |   Añade un nodo-texto a un nodo existente,      |
//  |   como ultimo hijo                              |
//  +-------------------------------------------------+

function agregarTextoAUnNodo(nodopadre, texto) {
    nodopadre.appendChild(document.createTextNode(texto));
}


//  +-------------------------------------------------+
//  |   borrarContenidoDeNodo                         |
//  |                                                 |
//  |   Elimina todos el contenido de un nodo         |
//  |   Se incluyen dos versiones                     |
//  +-------------------------------------------------+

function borrarContenidoDeNodo(nodo) {
    // VERSION 1: Borrar su innerHTML
    // nodo.innerHTML = '';

    // VERSION 2: Eliminar todos los hijos en un bucle
    while (nodo.firstChild) {
        nodo.removeChild(nodo.firstChild);
    }
}


//  +-------------------------------------------------+
//  |   Desordenar un array                           |
//  |                                                 |
//  +-------------------------------------------------+

function desordenaArray(unarray) {
    arraydesordenado = unarray.sort(function() { return Math.random() - 0.5 });
    return arraydesordenado;
}


function tamanoVentanaNavegador() {
    // Adaptada de http://www.howtocreate.co.uk/tutorials/javascript/browserwindow
    var dimensiones = [];

    if (typeof(window.innerWidth) == 'number') {
        // No es IE
        dimensiones = [window.innerWidth, window.innerHeight];
    } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
        //IE 6 en modo estandar (no quirks)
        dimensiones = [document.documentElement.clientWidth, document.documentElement.clientHeight];
    } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
        //IE en modo quirks
        dimensiones = [document.body.clientWidth, document.body.clientHeight];
    }

    return dimensiones;
}