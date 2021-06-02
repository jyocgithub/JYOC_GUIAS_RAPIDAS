// ===================================================================
//   OBJETOS EN JAVASCRIPT
// ===================================================================

// ===================================================================
//   OBJETOS LITERALES
// ===================================================================

// --- objeto literal sin funciones
var persona = { usuario: "Jose", apodo: "Pepe", edad: 50, jubilado: false }
document.write(persona.usuarioe + "<br>")
persona.jubilado = true

// --- objeto literal cin funciones
var objetoPersona = {
    nombre: "Pepe",
    edad: 23,
    currando: function(edadjubilacion) {
        return edadjubilacion - this.edad
    },
    tonteria: function() {
        var indice = 50

        function memez(indice) {
            var decima = indice / 10
            return decima
        }
    }
}
algo();


// --- objeto literal con funcion CONSTRUCTORA

function Coche(marca, matricula) {
    // propiedades publicas con this delante
    this.marca = marca;
    this.precio = 20;

    // propiedades privadas sin this delante
    var matricula = matricula;
    var iva = 21;

    // Los métodos públicos se declaran con this delante
    this.getMarca = function() { return this.marca; }
    this.getMatricula = function() { return matricula; }

    // Para las propiedades privadas, se puede hacer métodos públicos de acceso(getters y setters)
    this.setMatricula = function(mat) {
        matricula = comprobarMatricula(mat);
        this.getMatricula = function() { return matricula; }
    }
    this.setPrecio = function(pre) { this.precio = comprobarPrecio(this, pre); }

    // Si se desea usar métodos privados, no llevan this en la declaración.
    comprobarMatricula = function(matri) {
        if (matri.length != 7) {
            return "desconocida";
        }
        return matri;
    }

    // Dentro de un método privado no se puede acceder directamente a los atributos públicos de la clase, 
    // en tal caso, estos métodos deben recibir una referencia a this como parámetro
    comprobarPrecio = function(p_this, prec) {
        if (p_this.marca == "BMW") {
            return prec + 1000;
        }
        return prec;
    }

}

var micoche1 = new Coche("Opel", "4433DDS")