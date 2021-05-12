// Modificar todos los elementos <h1> con el texto“Come aquí”.
function paso2() {
    alert("Cambio h1");
    $('h1').html('Come aquí');
}

paso2();

// Modificar el primer elemento <h2> con el texto“Frutas”.
function paso3() {
    alert("Cambio primer h2");
    $('h2').eq(0).html('Frutas');
}

paso3();

// Modificar el segundo elemento <h2> con el texto“Vegetales”.
function paso4() {
    alert("Cambio segundo h2");
    $('h2').eq(1).html('Vegetales');
}

paso4();



// Modificar el tercer elemento <h2> con el texto“Pan”.
function paso5() {
    alert("Cambio tercer h2");
    //  $('h2').eq(2).html('Frutas'); 
    $('h2:eq(2)').html('Pan');

}

paso5();

//Modifica todos los elementos de la lista que estén a partir del índice3 para que aparezca
//“superior”.

function paso6() {
    alert("cambio elementos lista");

    $('li:gt(2)').html('superior'); // gt es greater that (mas que)m hay tambien lt (lower than)
}
paso6();

//Modifica el valor del campo de texto del formulario para que dentro aparezca “Introduce
//texto”.

function paso7() {
    alert("cambio input");
    $('input').val('introduce texto');
}

paso7(); *
/
//• Modifica el valor de todas las celdas pares de la tabla para que aparezca “0” y las impares
//para que aparezca “1”.


function paso8() {
    alert("cambio pares impares");
    $('td:even').html('0');
    $('td:odd').html('1');
}

paso8();

//Cambia todos los elementos que tengan un atributo“href” para que vayan a
//http://www.wikipedia.org

function paso9() {
    alert("cambio a wikipedia");
    $('[href]').html('http://www.wikipedia.org');
    $('a').attr("href", 'http://www.wikipedia.org');

}

paso9();

//• Cambia el texto de los párrafos de clase “parraf” para que aparezca “Entrada”.

function paso10() {
    alert("cambio a parraf");
    $('.parraf').html('Entrada');


}

paso10();