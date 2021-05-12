console.log("mensaje a la consola"); // escribe en la consola

// ===================== ARRAYS ====================
// - crear
var array = [];
var arra1 = [0, 0, 0];
var nombres = new Array("Juan", "Luis", "Ana", "Eva");
var arra2 = new Array(5);
// - meter
arra1.push(23); // meter un elemento al final del array
// - rellenar
arra2.fill(4); // llena el array con el valor dado
// - sacar
var elem = nombres.pop(); // elimina el ultimo elemento y lo devuelve
var elem = nombres.shift(); // elimina el primer elemento y lo devuelve
var elem = nombres.splice(2, 1); // quita 1 elemento desde la posición 2
// - borrar todo
nombres = []; // borra todos los elementos
// - recorrer
var nombres = new Array("Juan", "Pablo", "Ana", "Eva");
for (var i = 0; i < 4; i++) {
    alert(nombres[i]) // escribe uno a uno todos los nombres
}
for (i in arr) {
    alert(nombres[i])
}
//  Desordenar un array o matriz

function desordenaArray(unarray) {
    arraydesordenado = unarray.sort(function() { return Math.random() - 0.5 });
    return arraydesordenado;
}

// ===================== MATRICES ====================
var matriz = [
    [],
    []
]; // para dos dimensiones
var nombreMatriz = [
    ["Juan", "Luis", "Ana", "Eva"],
    ["22", "43", "55", "51"]
];
alert(nombreMatriz) // escribe Juan,Luis,Ana,Eva,22,43,55,51
alert(nombreMatriz[0]) // escribe Juan,Luis,Ana,Eva
alert(nombreMatriz[0][1]) // escribe Luis

var numfilas = nombres.length;
var numcols = nombres[0].length;

// crear la matriz con elementos vacios
var x = new Array(10);

for (var i = 0; i < x.length; i++) {
    x[i] = new Array(3);
}

// ===================== FUNCIONES CALLBACK  ====================
// - con arrays
var contenido = "";

function escribeContenido(value, index, array) {
    contenido = contenido + value + "<br>"
}

var nums = [64, 25, 13, 24, 18, 11, 43];
nums.forEach(escribeContenido)
document.write(contenido)



// ===================== DOM ====================
window.onload = iniciar; // para iniciar ua funcion trascarga del DOM
var x = document.getElementById("idarriba");
var x = document.getElementsByClassName("classarriba");
var tabla = document.createElement("table");
tabla.style.border = "1px solid black";
tabla.appendChild(fila);