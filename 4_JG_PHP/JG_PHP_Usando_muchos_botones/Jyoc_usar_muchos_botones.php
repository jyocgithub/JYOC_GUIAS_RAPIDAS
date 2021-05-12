<?php


//------------------- SI DOS BOTONES PUEDEN TENER DISTINTO NOMBRE, EJEMPLO 1
// Usamos el elemento input de type=submit, con el mismo name, modificando su value y preguntando por él en $_POST
$texto =    '
<!DOCTYPE html>
<html>
<body>
<form action="validar.php" method="post">
Nombre: <input type="text" nombre="nombre"><br>
Contraseña: <input type="text" nombre="password"><br>
<input type="submit" name="local"  value="Entrar local" />
<input type="submit" name="remoto" value="Entrar remoto" />
</form>
</body>
</html>';
echo $texto;

if (isset($_POST['local'])) {
    // instrucciones para el primer boton
}
if (isset($_POST['remoto'])) {
    // instrucciones para el primer boton
}

//------------------- SI DOS BOTONES PUEDEN TENER DISTINTO NOMBRE, EJEMPLO 2
$texto =    '
<!DOCTYPE html>
<html>
<body>
<form action="validar.php" method="post">
Nombre: <input type="text" nombre="nombre"><br>
Contraseña: <input type="text" nombre="password"><br>
<input type="submit" name="acceso" value="Entrar local" />
<input type="submit" name="acceso" value="Entrar remoto" />
</form>
</body>
</html>';
echo $texto;

if (isset($_POST['acceso'])) {
    if ($_POST['acceso'] == 'Entrar local') {
        // instrucciones para el primer boton
    }
    if ($_POST['acceso'] == 'Entrar remoto') {
        // instrucciones para el primer boton
    }
}

//------------------- SI DOS BOTONES NO PUEDEN TENER DISTINTO NOMBRE, HAN DE TENER EL MISMO
// Usamos el elemento Button, con el mismo name, modificando su value y dando contenido fuera 
$texto =    '
<!DOCTYPE html>
<html>
<body>
<form action="validar.php" method="post">
  Seleccione tipo de transporte:<br>
     Avion <button type="submit" name="viaje" value="avion">Elegir</button>
     Tren  <button type="submit" name="viaje" value="tren" >Elegir</button>
</form>
</body>
</html>';
echo $texto;

if (isset($_POST['viaje'])) {
    if ($_POST['viaje'] == 'avion') {
        // instrucciones para el primer boton
    }
    if ($_POST['viaje'] == 'tren') {
        // instrucciones para el primer boton
    }
}
