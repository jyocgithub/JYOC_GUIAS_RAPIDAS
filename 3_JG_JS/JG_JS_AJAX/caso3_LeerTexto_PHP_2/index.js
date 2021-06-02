addEventListener("load", inicializarEventos, false);

function inicializarEventos() {
    var ob = document.getElementById("Enviar");
    ob.addEventListener("click", presionBoton, false);
}

function presionBoton(e) {
    e.preventDefault();
    var nombre = document.getElementById("nomProvincia").value;
    alert("Enviando a servidor : " + nombre);
    cargarLocalidad(nombre);
}

var conexion1;

function cargarLocalidad(nombre) {
    conexion1 = new XMLHttpRequest();
    conexion1.onreadystatechange = procesarEventos;
    conexion1.open("GET", 'procesar.php?nomLocalidad=' + nombre, true);
    conexion1.send();
}

function procesarEventos() {
    var resultado = document.getElementById("resultado");
    if (conexion1.readyState == 4 && conexion1.status == 200) {
        resultado.innerHTML = conexion1.responseText;
    } else {
        resultado.innerHTML = "Cargando..."
    }
}