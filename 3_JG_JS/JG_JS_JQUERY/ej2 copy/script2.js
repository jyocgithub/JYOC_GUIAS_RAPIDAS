// • Ponle borde a la tabla mediante el atributo border. 
function paso1() {
    $('table').css('border', '2px solid black');
}
paso1();

//• Asigna a las filas de encabezado de la parte superior un borde doble. 
function paso2() {
    //  $('th:lt(2)').css('border','3px double black'); 
    $('tr:eq(0) th').css('border', '3px double black');
}
paso2();

//Asigna a las filas de encabezado de la parte inferior solo borde superior. El resto no se
//verán. 
function paso3() {
    $('tr:last th').css('border-top', '3px solid black');
}
paso3();

//• Asigna a las celdas pares la clase .rojo.
function paso4() {
    $('td:even').addClass("rojo");
}
paso4();

//• Asigna a las celdas impares la clase .azul. 
function paso5() {
    $('td:odd').addClass("azul");
}
paso5();

//• Asigna a las celdas de encabezado la clase .negrita. 
function paso6() {
    $('th').addClass("negrita");
}
paso6();

//• Aumenta el alto de las celdas de encabezado para que sea diferente del predeterminado. 
function paso7() {
    $('td').height("2em");
}
paso7();

//• Oculta la fila 5 (método .hide()). 
function paso8() {
    $('tr:eq(4)').hide();
}
paso8();

//Pon en amarillo todas las celdas que tengan el texto "Amarillo". 
function paso9() {
    $('td:contains("Amarillo")').css('background-color', 'yellow ');
}
paso9();