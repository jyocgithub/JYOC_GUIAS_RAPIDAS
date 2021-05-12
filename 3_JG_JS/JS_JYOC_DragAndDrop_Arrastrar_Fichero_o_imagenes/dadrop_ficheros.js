window.onload = function() {
    var descarga_ficheros = document.getElementById('id_descarga_ficheros');

    // Opcional. Muestra el icono de "copiar" mienstras se arrastra 
    // Parece funcionar solo en Chrome

    descarga_ficheros.addEventListener('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault(); //  turn off the browser's default drag and drop handler.
        e.dataTransfer.dropEffect = 'copy';
    });

    descarga_ficheros.addEventListener('drop', function(e) {
        e.stopPropagation();
        e.preventDefault(); //   turn off the browser 's default drag and drop handler.
        var files = e.dataTransfer.files;

        for (var i = 0; i < files.length; i++) {
            // for (var i = 0, file; file = files[i]; i++) {
            file = files[i];
            if (e.dataTransfer.items[i].kind === 'file') {
                var file = e.dataTransfer.items[i].getAsFile();
                var et_p = document.createElement('p');
                et_p.innerHTML = file.name;
                document.getElementById("id_zona_recepcion_ficheros").appendChild(et_p);
                // console.log('... file[' + i + '].name = ' + cadafile.name);
                console.log('... file[' + i + '].name = ' + file.name);

            }


            //     cadafile = files[i];
            //     // for (var i = 0, file; file = files[i]; i++) {
            //     if (cadafile.kind === 'file') {
            //         var file = cadafile.getAsFile();
            //         var et_p_fichero = document.createElement('p');
            //         et_p_fichero.innerHTML = cadafile.name;
            //         document.getElementById("id_zona_recepcion_ficheros").appendChild(et_p_fichero);
            //         console.log('... file[' + i + '].name = ' + cadafile.name);
            //     }

        }
    });
};




// for (var i = 0, file; file = files[i]; i++) {
//     if (e.dataTransfer.items[i].kind === 'file') {
//         var file = e.dataTransfer.items[i].getAsFile();
//         var et_p_fichero = document.createElement('p');
//         et_p_fichero.innerHTML = file.name;
//         document.getElementById("id_zona_recepcion_ficheros").appendChild(et_p_fichero);
//         // console.log('... file[' + i + '].name = ' + cadafile.name);
//         console.log('... file[' + i + '].name = ' + file.name);

//     }