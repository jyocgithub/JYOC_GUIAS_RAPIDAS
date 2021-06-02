window.onload = function() {
    leerFicherosDeInputFile();

    var descarga_imagenes = document.getElementById('id_zona_descarga_imagenes');
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
        var contadorImagenesCargadas = 0;
        var listanombres = [];

        for (var i = 0; i < files.length; i++) {
            file = files[i];
            listanombres.push(file.name);

            if (file.type.match(/image.*/)) {
                var reader = new FileReader();

                reader.onload = function(elemento) {
                    // funcion que se lanza cuando se acaba de leer el fichero
                    var et_img = document.createElement('img');
                    et_img.src = elemento.target.result;
                    et_img.setAttribute("height", "100");
                    et_img.setAttribute("width", "100");
                    document.getElementById("id_zona_vision_imagenes").appendChild(et_img);

                    var et_p = document.createElement('p');
                    et_p.innerHTML = listanombres[contadorImagenesCargadas];
                    contadorImagenesCargadas++;
                    document.getElementById("id_zona_vision_imagenes").appendChild(et_p);

                }
                reader.readAsDataURL(file); // comienzo de lectura del fichero
            }
        }
    });
};



function leerFicherosDeInputFile() {
    if (window.FileList && window.File) {
        document.getElementById('id_input_file').addEventListener('change', event => {
            // output.innerHTML = '';
            for (const file of event.target.files) {
                const li = document.createElement('li');
                const name = file.name;
                // const name = file.name ? file.name : 'NOT SUPPORTED';
                // const type = file.type ? file.type : 'NOT SUPPORTED';
                // const size = file.size ? file.size : 'NOT SUPPORTED';
                console.log(name);
                // li.textContent = `name: ${name}, type: ${type}, size: ${size}`;
                // output.appendChild(li);
            }
        });
    }
}