window.onload = lanzarPeticionAJAX;

var miPeticionAJAX;

function lanzarPeticionAJAX() {
    miPeticionAJAX = new XMLHttpRequest();
    // miPeticionAJAX.responseType = 'json';
    miPeticionAJAX.onreadystatechange = transcurrePeticionAJAX;
    miPeticionAJAX.open("GET", 'lecturaLibros.php', true);
    miPeticionAJAX.send();
}

function transcurrePeticionAJAX() {
    var respuestas = document.getElementById("respuestas");

    if (miPeticionAJAX.readyState == 4 && miPeticionAJAX.status == 200) {
        respuestas.innerHTML = "";
        // var arrayLibros = miPeticionAJAX.responseText;
        var arrayLibros = JSON.parse(miPeticionAJAX.responseText);
        console.log(arrayLibros);
        procesarEventos(arrayLibros);
    } else {
        respuestas.innerHTML = "Cargando...";
    }
}

function procesarEventos(arrayLibros) {
    var respuesta = document.getElementById("respuestas");

    respuestas.innerHTML = '';
    var tabla = document.createElement("table");
    tabla.style.border = "1 px solid black ";
    respuestas.appendChild(tabla);
    for (var i = 0; i < arrayLibros.length; i++) {
        var cadafila = arrayLibros[i];
        var fila = document.createElement("tr");;

        var celda1 = document.createElement("td");
        var celda2 = document.createElement("td");
        var celda3 = document.createElement("td");
        var celda4 = document.createElement("td");
        var celda5 = document.createElement("td");
        celda1.style.border = "1px solid black ";
        celda2.style.border = "1px solid black ";
        celda3.style.border = "1px solid black ";
        celda4.style.border = "1px solid black ";
        celda5.style.border = "1px solid black ";
        celda1.setAttribute("width", 100);
        celda2.setAttribute("width", 300);
        celda3.setAttribute("width", 200);
        celda4.setAttribute("width", 100);
        celda5.setAttribute("width", 100);

        celda1.innerHTML = cadafila['codigo_de_libro'];
        celda2.innerHTML = cadafila['titulo'];
        celda3.innerHTML = cadafila['autor'];
        celda4.innerHTML = cadafila['editorial'];
        celda5.innerHTML = cadafila['precio'];

        fila.appendChild(celda1);
        fila.appendChild(celda2);
        fila.appendChild(celda3);
        fila.appendChild(celda4);
        fila.appendChild(celda5);
        tabla.appendChild(fila);

    }
}
// function crearTabla() {

//     tabla.style.border = "1px solid black";

//     var fila = document.createElement("tr");

//     var columnatitulo = document.createElement("td");
//     var columnacadena = document.createElement("td");
//     columnacadena.style.border = "1px solid black";
//     columnatitulo.style.border = "1px solid black";
//     columnatitulo.innerHTML = arraytitulos[i];
//     columnacadena.innerHTML = arraycadenas[i];

//     fila.appendChild(columnatitulo);
//     fila.appendChild(columnacadena);

//     tabla.appendChild(fila);

//     container.appendChild(tabla);
// }