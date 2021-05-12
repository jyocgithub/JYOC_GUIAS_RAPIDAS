window.onload = lanzarPeticionAJAX;

var miPeticionAJAX;
var arraytitulos = [];
var arraycadenas = [];

function lanzarPeticionAJAX() {

    miPeticionAJAX = new XMLHttpRequest();
    miPeticionAJAX.onreadystatechange = transcurrePeticionAJAX;
    miPeticionAJAX.open("GET", 'peliculas.xml', true);
    miPeticionAJAX.send();
}

function transcurrePeticionAJAX() {
    var resultado = document.getElementById("resultado");

    if (miPeticionAJAX.readyState == 4 && miPeticionAJAX.status == 200) {
        resultado.innerHTML = "";
        procesarEventos();
    } else {
        resultado.innerHTML = "Cargando...";
    }
}

function procesarEventos() {
    var xmlDoc = miPeticionAJAX.responseXML;

    var listaDeNodosSerie = xmlDoc.getElementsByTagName("serie");

    for (let i = 0; i < listaDeNodosSerie.length; i++) {

        let todoslostitulos = listaDeNodosSerie[i].getElementsByTagName("titulo");
        let primertitulo = todoslostitulos[0]; // como solo hay uno. cojo el primero y punto
        let todosloshijosdelprimertitulo = primertitulo.childNodes;
        let eltextodeltitulo_elprimerhijo_queeseltexto = todosloshijosdelprimertitulo[0].nodeValue;

        // ahora con la cadena, todo lo de antes en una sola linea 
        let cadena = listaDeNodosSerie[i].getElementsByTagName("cadena")[0].childNodes[0].nodeValue;

        arraytitulos.push(eltextodeltitulo_elprimerhijo_queeseltexto);
        arraycadenas.push(cadena);
        // alert(eltextodeltitulo_elprimerhijo_queeseltexto + " : " + cadena);
    }
    crearTabla();
}

function crearTabla() {
    var container = document.getElementById("container");

    var tabla = document.createElement("table");
    tabla.style.border = "1px solid black";

    for (var i = 0; i < arraytitulos.length; i++) {
        var fila = document.createElement("tr");

        var columnatitulo = document.createElement("td");
        var columnacadena = document.createElement("td");
        columnacadena.style.border = "1px solid black";
        columnatitulo.style.border = "1px solid black";
        columnatitulo.innerHTML = arraytitulos[i];
        columnacadena.innerHTML = arraycadenas[i];

        fila.appendChild(columnatitulo);
        fila.appendChild(columnacadena);

        tabla.appendChild(fila);
    }

    container.appendChild(tabla);
}