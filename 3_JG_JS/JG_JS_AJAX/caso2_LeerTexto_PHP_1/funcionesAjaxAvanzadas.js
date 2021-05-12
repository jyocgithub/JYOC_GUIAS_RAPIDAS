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



//  +--------------------------------------------------------------------------------+
//  |   FUNCIONES QUE OBTIENEN UN OBJETO XHR EVALUANDO QUE NAVEGADOR ESTAMOS USANDO  |
//  |                                                                                |
//  |   PUEDE NO USARSE Y CREAR EL OBJETO MAS DIRECTAMENTE                           |
//  |   completamente el arbol DOM de elementos                                      |
//  +--------------------------------------------------------------------------------+
function obtenerObjetoXHR() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}
// === OTRA VERSION AUN MAS DETALLADA
function obtenerObjetoXHRAvanzado() {
    if (window.XMLHttpRequest) {
        // El navegador implementa la interfaz XHR de forma nativa
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        var versionesIE = new Array(
            "Msxml2.XMLHTTP.5.0",
            "Msxml2.XMLHTTP.4.0",
            "Msxml2.XMLHTTP.3.0",
            "Msxml2.XMLHTTP",
            "Microsoft.XMLHTTP"
        );
        for (var i = 0; i < versionesIE.length; i++) {
            try {
                return new ActiveXObject(versionesIE[i]);
            } catch (errorControlado) {} //Capturamos el error,
        }
    }
    // Si llegamos aquí es porque el navegador no posee ninguna forma de crear el objeto.
    throw new Error("No se pudo crear el objeto XMLHttpRequest");
}