// ===================================================================
//   CUATRO FORMAS DE AÑADIR UN JAVASCRIPT A UN ELEMENTO HTML
// ===================================================================

// 1.- Escribir el código Javascript en un fichero externo, y añadir la referencia al fichero en el HTML:
// < script type = "text/javascript" src = “miScript1.js” > </script> <


// 2. - Escribir el código Javascript dentro del HTML, en un bloque script interno (normalmente dentro del HEAD)
// script type = "text/javascript" >
//     // Aqui pueden ir las funciones y codigo js a usar en esta pagina
// </script>
// Se pueden escribir tantos scripts como se desee

// 3. - Escribir el código Javascript en un script, pero en cualquier parte del HTML
// Se puede colocar en mitad de un HTML y se ejecuta directamente al procesarse
// < p id = "parrafo1" > texto que va a cambiar < /p><br> 
// < script >
//     document.getElementById("parrafo1").innerHTML = "nuevo texto al pasar por aqui"; 
// </script>


// 4. - Escribir el código Javascript dentro de un elemento especifico del HTML(en un evento p.e.) 
// <button type = "button" id = "boton1" onclick = "document.getElementById('boton1').innerHTML = Date()" >
//     Haz click en mi y veras la hora 
// </button>

// ===================================================================
//   CUATRO FORMAS DE ESCRIBIR ALGO EN JAVASCRIPT
// ===================================================================

// 1. - Escribir algo directamente en el html 
document.write("hola ");
// CUIDADO: solo valido para pruebas, la llamada a document.write en un documento ya cargado limpiará todo el contenido del documento

// 2. - Escribir algo en una alerta
alert("hola pollo");

// 2. - Escribir algo en consola (ideal para pruebas)
console.log("hola pollo");

// 2. - Escribir algo en un elemento del html, que tenga un id dado:
document.getElementById("idDelElemento").innerHTML = "hola pollo ";



// ===================================================================
//   AMBITO DE LAS VARIABLES
// ===================================================================

var uno = 113; // es variable global, esta fuera de toda funcion
var dos = 234; // es variable global, esta fuera de toda funcion
tres = 344; // es variable global, se crea y tambien esta fuera de toda funcion
let seis = 234; // una variable let a nivel gloabl SOLO se ve a nivel global, NO DENTRO DE FUNCIONES
const MESES = 3; // una constante, aplica ser global o local a lo mismo que las demas

function pruebasvariables() {
    uno = 999; // es global, coindice con la declarada anteriormente
    var cuatro = 32; // es local, y local a esta funcion, donde se ha declarado
    var siete = 32; // es tambien local, y local a esta funcion, donde se ha declarado
    var dos = 4235; // es LOCAL, se declara NUEVAMENTE. Dentro de esta funcion es distinta a la global de mismo nombre
    cuatro = 22; // se accede nuevamente a la variable local, no se crea ningua nueva
    tres = 33243; // se accede nuevamente a la variable GLOBAL, no se crea ningua nueva
    var t; // si no asignas valor a una variable, es obligatorio declararla con la palabra var
    cinco = "Esta es global" // creada sin var, NO EXISTIA ANTES, luego es GLOBAL
}
pruebasvariables()
console.log(tres)
console(cuatro) // no se puede acceder a ella, es local a la funcion



// ===================================================================
//   TIPOS DE LAS VARIABLES
// ===================================================================

console.log(typeof ""); // escribe "string"
ft = typeof "Paloma"; // f vale "string"
console.log(ft + " Feliz "); // escribe "String Feliz"
console.log(typeof 234); // escribe "number"
console.log(typeof 3.34); // escribe "number
console.log(typeof(33 == 12)); // escribe "boolean"
console.log(typeof [1, 2, 3, 4]); // escribe "object", pues los arrays son objetos
console.log(typeof { Marca: 'Opel', Precio: 34000 }); // escribe "object", pues es un objeto
var ff = function(a) { return a * a; };
console.log(typeof ff); // escribe "function"
console.log(typeof undefined); // escribe "undefined"
console.log(typeof null); // escribe "object"
console.log(null === undefined); // escribe "false"
console.log(null == undefined); // escribe "true"