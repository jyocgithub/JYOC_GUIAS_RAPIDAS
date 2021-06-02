<?php

// -------------------- CONFIGURACION INICIAL
//
// PHP debe estar configurado para permitir descargar archivos.
// En el archivo "php.ini" que se usae en su ejecución, 
// se ha de confirmar que la directiva file_uploads esta con valor On
// ----> file_uploads = On

// Inicialmente escribimos la parte de descarga de ficheros:
$textohtml = '
<form action="Jyoc_guia_descarga.php" method="post" enctype="multipart/form-data">
  Seleccione una imagen a descargar:<br>
  <input type="file" name="fichero" id="fichero"><br>
  <input type="submit" value="Descargar" name="descarga"><br>
</form>
';
echo $textohtml;

// A este if accederemos solo tras seleccionar en el boton "Descargar"
if (isset($_POST['descarga'])) {

    // preparamos variables con los valores que vamos a usar

    $directorio_destino = '/Applications/XAMPP/xamppfiles/htdocs/JYOCPHP_Guia_Descargar_Fichero/descargas/';
    $fichero_destino = $_FILES["fichero"]["name"];
    $fichero_destino_con_path = $directorio_destino . basename($fichero_destino);
    $fichero_origen_tipo = strtolower(pathinfo($fichero_destino_con_path, PATHINFO_EXTENSION));
    $fichero_origen_nombre = $_FILES["fichero"]["tmp_name"];
    $fichero_origen_tamano = $_FILES["fichero"]["size"];
    $descarga_realizada = true;
    $tamano_maximo_de_descarga = 1000000;

    $es_una_imagen = getimagesize($fichero_origen_nombre); // Mirar si lo que dice ser una imagen lo es realmente
    if ($es_una_imagen === false) { // getimagesize() devuelve informacion de un fichero, solo si es una imagen
        echo "El fichero elegido no parece una imagen.";
    } else {
        if (file_exists($fichero_destino_con_path)) { // No subimos una imagen si ya se habia subido antes
            echo "Lo siento, ya existe un fichero con ese nombre";
            $descarga_realizada = false;
        } else {
            echo "Descargando " . $fichero_origen_tamano . " bytes....";
            if ($fichero_origen_tamano > $tamano_maximo_de_descarga) { // // No subimos una imagen si pesa mucho
                echo "No se pueden subir imagenes de mas de " . $tamano_maximo_de_descarga . " bytes";
                $descarga_realizada = false;
            } else {
                if ($fichero_origen_tipo != "jpg" && // Solo se permiten descargas de imagenes
                $fichero_origen_tipo != "png" && $fichero_origen_tipo != "jpeg" && $fichero_origen_tipo != "gif") {
                    echo "<br>Solo se permite descargar ficheros  JPG, JPEG, PNG o GIF";
                    $descarga_realizada = false;
                } else {
                    if (move_uploaded_file($fichero_origen_nombre, $fichero_destino_con_path)) {
                        echo "<br>Fichero " . htmlspecialchars(basename($fichero_destino)) . " descargado.";
                    } else {
                        echo "<br>Hubo un error en la descarga del fichero";
                        $descarga_realizada = false;
                    }
                }
            }
        }
    }
    if ($descarga_realizada == false) {
        echo "<br>El fichero no se ha descargado.";
    }
}


