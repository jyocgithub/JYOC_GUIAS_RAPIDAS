window.onload = function() {
    var descarga_imagenes = document.getElementById('id_descarga_imagenes');

    // Opcional. Muestra el icono de "copiar" mienstras se arrastra 
    // Parece funcionar solo en Chrome

    descarga_imagenes.addEventListener('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault();
        e.dataTransfer.dropEffect = 'copy';
    });

    descarga_imagenes.addEventListener('drop', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var files = e.dataTransfer.files;

        for (var i = 0; i < files.length; i++) {
            // for (var i = 0, file; file = files[i]; i++) {
            file = files[i];
            if (file.type.match(/image.*/)) {
                var reader = new FileReader();

                reader.onload = function(elemento) {
                    // funcion que se lanza cuando se acaba de leer el fichero
                    var et_img = document.createElement('img');
                    et_img.src = elemento.target.result;
                    et_img.setAttribute("height", "100");
                    et_img.setAttribute("width", "100");
                    document.getElementById("id_zona_recepcion_imagenes").appendChild(et_img);

                    var et_p = document.createElement('p');
                    et_p.innerHTML = elemento.target.filename;
                    // et_p.innerHTML = file.name;
                    document.getElementById("id_zona_recepcion_imagenes").appendChild(et_p);

                }

                reader.readAsDataURL(file); // comienzo de lectura del fichero
            }
        }
    });
};