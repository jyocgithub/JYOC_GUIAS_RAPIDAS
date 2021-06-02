window.onload = lanzarPeticionAJAX;

var peticionAJAX;

function lanzarPeticionAJAX() {
    // Obtener la instancia del objeto XMLHttpRequest
    if (window.XMLHttpRequest) {
        //Navegadores estándar
        peticionAJAX = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        //Navegadores obsoletos
        peticionAJAX = new ActiveXObject("Microsoft.XMLHTTP");
    }
    // Preparar la funcion de respuesta
    peticionAJAX.onreadystatechange = resultadosPeticionAJAX;
    // Realizar peticion HTTP. Get sin envío de parámetros (null)
    peticionAJAX.open("GET", "http://localhost/JYOCJS_AJAX/caso1/hola.txt", true);
    peticionAJAX.open("GET", "hola.txt", true);
    peticionAJAX.send(null);

}

function resultadosPeticionAJAX() {
    if (peticionAJAX.readyState === 4) {
        //Ha recibido una respuesta
        if (peticionAJAX.status === 200) {
            //La respuesta es válida y correcta
            alert(peticionAJAX.responseText);
        }
    }
}