/* Programar una función desde la que se ejecuten los procesos necesarios
después de cargar la página html en memoria. */


/* Crear una función en la cual una conexión asíncrona obtenga los datos de 
la tabla familia de la base de datos. */

/* Con los datos obtenidos en formato JSON añadir a la etiqueta
<select id="listaFamilias"> tantas <option> como registros tenga la tabla familias.
Cada <option> guarda el id de la familia, el nombre y la foto. */

/* Programar una función para el evento change de la <select>
que visualice en la etiqueta <input type="text" id="familiaSeleccionada" />
el nombre de la familia y la etiqueta <img src="" id="imagenFamilia" alt="Imagen Familia" />
la imagen, foto, de la familia seleccionada. */

/* Crear un evento que al hacer click en el botón,
<input type="button" id="leerProductos" value="Leer Productos Almacén por Familia" />.
En el evento creado programar una conexión asíncrona que ejecute el php
seleccionArticulos.php pasandole el id de la familia seleccionada en la <select>. */

/* Con los datos Json obtenidos por la conexión asíncrona,
crear el código html necesario para que se visualicen en la etiqueta html
<article id="listaArticulos" class="header4" aquellos artículos
cuyo stock sea mayor que el stock mínimo. */

$(inicio);


var parametros = '';
var miPeticionAJAX;
var arrayFamilias = [];

function inicio() {
    $("#imagenFamilia").hide();
    lanzarPeticionAJAXFamilias();
    $("#leerProductos").click(lanzarPeticionAJAXProductos);
    $("#listaFamilias").change(familiaSeleccionada);

}

function familiaSeleccionada() {
    var valor = $("#listaFamilias option:selected").text();
    $("#familiaSeleccionada").val(valor);

    for (var i = 0; i < arrayFamilias.length; i++) {
        if (arrayFamilias[i].nombreFamilia == valor) {
            var contenidoImagen = 'data:image/png;base64,' + arrayFamilias[i].foto;
            $("#imagenFamilia").attr('src', contenidoImagen);
            $("#imagenFamilia").show().css('display', 'block').css('margin-left', 'auto').css('margin-right', 'auto');
            $("#imagenFamilia").width(200).height(200);
        }
    }
    var contenidoImagen = 'data:image/png;base64,' + arrayFamilias[0].foto;
    $("#imagenFamilia").attr('src', contenidoImagen);
}

// ************************************* PEDIR FAMILIAS
function lanzarPeticionAJAXFamilias() {
    var urlcompleta = 'familias_json.php';
    $.ajax({
        url: urlcompleta,
        type: "POST",
        dataType: "json",
        success: function(result) {
            procesarRespuestaFamilias(result);
        }
    });
}

function procesarRespuestaFamilias(resultado) {
    arrayFamilias = resultado;
    for (var i = 0; i < arrayFamilias.length; i++) {
        var op = $("<option></option>").text(arrayFamilias[i].nombreFamilia);
        op.attr("value", arrayFamilias[i].nombreFamilia);
        $("#listaFamilias").append(op);
    }
}

// ***************************************************** PRODUCTOS
function lanzarPeticionAJAXProductos() {
    var nombrebuscado = $("#familiaSeleccionada").val();
    var urlcompleta = 'productos_json.php';
    // var urlcompleta = 'productos_json.php' + parametros;
    $.ajax({
        data: {
            "nombrebuscado": nombrebuscado
        },
        url: urlcompleta,
        type: "POST",
        dataType: "json",
        success: function(result) {
            procesarRespuestaProductos(result);
        }
    });
}

function procesarRespuestaProductos(resultado) {
    arrayProductos = resultado;
    crearTabla();
}


function crearTabla() {
    $("#listaArticulos").html('');
    var tabla = $("<table></table>").css("border", "1px solid black");
    $("#listaArticulos").append(tabla);
    var anchocelda = "200px";
    for (var i = 0; i < arrayProductos.length; i++) {
        var celda1 = $("<td></td>").text(arrayProductos[i].id).css("border", "1px solid black").css("width", anchocelda);
        // var celda2 = $("<td></td>").text(arrayProductos[i].idFamilia).css("border", "1px solid black").css("width", "40%");
        var celda3 = $("<td></td>").text(arrayProductos[i].descripcion).css("border", "1px solid black").css("width", anchocelda);
        var celda4 = $("<td></td>").text(arrayProductos[i].precioCoste).css("border", "1px solid black").css("width", anchocelda);
        var celda5 = $("<td></td>").text(arrayProductos[i].precioVenta).css("border", "1px solid black").css("width", anchocelda);
        var celda6 = $("<td></td>").text(arrayProductos[i].stock).css("border", "1px solid black").css("width", anchocelda);
        // var celda7 = $("<td></td>").text(arrayProductos[i].codProv).css("border", "1px solid black").css("width", "10%");
        var celda8 = $("<td></td>").text(arrayProductos[i].stockMin).css("border", "1px solid black").css("width", anchocelda);
        var celda9 = $("<td></td>").css("border", "1px solid black").css("width", anchocelda);
        var celda10 = $("<td></td>").text(arrayProductos[i].fecha).css("border", "1px solid black").css("width", anchocelda);

        var imagen = new Image();
        imagen.src = 'data:image/png;base64,' + arrayProductos[i].foto;

        var idcelda9 = "idcelda_9" + i;
        celda9.attr("id", idcelda9);

        var fila = $("<tr></tr>").append(celda1).append(celda3).append(celda4).append(celda5).append(celda6).append(celda8).append(celda9).append(celda10);
        tabla.append(fila);

        $("#" + idcelda9).append(imagen); // ha de se lo ultimo !!!!!!!!!
    }
}

// class Alumno {
//     constructor(codigo, nombre, apellidos, fecha_nacimiento, curso, nota_media) {

//         this.codigo = codigo || 0;
//         this.nombre = nombre || "";
//         this.apellidos = apellidos || "";
//         this.nota_media = nota_media || 0;
//         this.curso = curso || "";
//         this.fecha_nacimiento = fecha_nacimiento || "";

//     }
// }