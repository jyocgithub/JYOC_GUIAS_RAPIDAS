// ===================================================================
//   FECHAS EN JAVASCRIPT
// ===================================================================

// ===================================================================
//   CREAR FECHA
// ===================================================================

var date = new Date("2001-12-31");
var date2 = new Date(1998, 9, 30, 13, 40, 5);
var date3 = new Date(1223727718982);
console.log(date);
console.log(date2);
console.log(date3);


var datehoy = new Date();
console.log(datehoy); // en formato fecha
var datehoy2 = Date.now();
console.log(datehoy2); // en timpestamp

// ===================================================================
//   FORMATEAR FECHA
// ===================================================================

var fechaformateada = datehoy.toLocaleDateString();
var fechaformateadaSpain = Intl.DateTimeFormat("es-ES").format(datehoy)
console.log(fechaformateada);      // formato mas/dia/año
console.log(fechaformateadaSpain); // formato dia/mes/año



// ===================================================================
//   COMPARAR FECHAS
// ===================================================================
// comparar puede ser con > o < pero para igual se usa timestamp
if (date > date2) {
    console.log("mayor " + date);
} else {
    console.log("mayor " + date2);
}


var date8 = new Date(1998, 9, 30, 13, 40, 5);
var date9 = new Date(1998, 9, 30, 13, 40, 5);
// if (date8 === date9) {    // NO FUNCIONA, son objetos, asi compara referencias
if (date8.getTime() === date9.getTime()) {
    console.log("iguales");
} else {
    console.log("distintas");

}



// ===================================================================
//   OBTENER INFORMACION DE LA FECHA
// ===================================================================
console.log(date.getFullYear()) // 2001
console.log(date.getMonth()) // 11 (enero empieza en 0x)
console.log(date.getDate()) // 31
console.log(date.getDay()) // 1 ( 0-domingo, 1-lunes, 2-martes...)
console.log(date.getHours()) // 1
console.log(date.getMinutes()) // 0



// ===================================================================
//   CAMBIAR CONTENIDO DE UNA FECHA
// ===================================================================
date.setYear(1998);
date.setMonth(4);
date.setDate(12);
date.setHours(12);
date.setMinutes(21);


// ===================================================================
//   DIAS ENTRE DOS FECHAS
// ===================================================================

var fecha1 = new Date('2011/03/17');
var fecha2 = new Date()

var resta = fecha2.getTime() - fecha1.getTime()   // esto resta milisegundos
console.log(Math.round(resta/ (1000*60*60*24)))   // usamos 1000*60*60*24 para dividir milisegundos en DIAS
