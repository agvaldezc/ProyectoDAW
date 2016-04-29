/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function trim(value) {
   var temp = value;
   var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
   if (obj.test(temp)) { temp = temp.replace(obj, '$2');}
   var obj = /  /g;
   while (temp.match(obj)) { temp = temp.replace(obj, " ");}
   return temp;
}

//Funcion para calcular el largo en pixels det texto dado
function getTextWidth(texto)
{
	//Valor por default : 150 pixels
	var ancho = 150;

	if(trim(texto) == "")
	{
		return ancho;
	}

	//Creaci�n de un span escondido que se puedr� medir 
	var span = document.createElement("span");
	span.style.visibility = "hidden";
	span.style.position = "absolute";

	//Se agrega el texto al span y el span a la p�gina
	span.appendChild(document.createTextNode(texto));
	document.getElementsByTagName("body")[0].appendChild(span);

	//tama�o del texto
	ancho = span.offsetWidth;

	//Eliminaci�n del span
	document.getElementsByTagName("body")[0].removeChild(span);
	span = null;

	return ancho;
}


//Funcion de modificacion del elemento seleccionado mediante doble-click
function modificar(obj, tipo)
{ 
	//Objeto que sirve para editar el valor en la pagina 
	var input = null;

	input = document.createElement("input");


	//Asignar en la caja el valor de la casilla
	if (obj.innerText)
		input.value = obj.innerText;
	else
		input.value = obj.textContent;
	input.value = trim(input.value);

	//a la caja INPUT se la asigna un tama�o un poco mayor que el texto a modificar
	input.style.width  = getTextWidth(input.value) + 30 + "px";

	//Se remplaza el texto por el objeto INPUT
	obj.replaceChild(input, obj.firstChild);

	//Se selecciona el elemento y el texto a modificar
	input.focus();
	input.select();

	//Asignaci�n de los 2 eventos que provocar�n la escritura en la base de datos 

      //Salida de la INPUT
	input.onblur = function salir()
	{
		salvarMod(obj, input.value);

		guardarCambios(input.value, obj.id, obj.parentNode.id);

		delete input;
	};

	//La tecla Enter
	input.onkeydown = function keyDown(event)
	{
		if(event.keyCode == 13)
        {
			salvarMod(obj, input.value);
                        guardarCambios(input.value, obj.id, obj.parentNode.id);
			delete input;
		}
	};
}


//Salvando las modificaciones
function salvarMod(obj, valor)
{
obj.replaceChild(document.createTextNode(valor), obj.firstChild);

}

function guardarCambios(cambio, columna, registro) {
	var req = new XMLHttpRequest();

	var url =  "guardarCambios.jsp?cambio=" + cambio + "&columna=" + columna + "&registro=" + registro;

	req.open("GET",url , true);

	req.send(null);

}

function agregarFila() {

	var req = new XMLHttpRequest();

	req.onload = function() { agregarFilaVista(req) };

	req.open("GET", "agregarRegistro.php", true);

	req.send(null);
}

function agregarFilaVista(req) {

	if (req.status == 200) {
		var tabla = document.getElementById("tabla-usuarios");

		var tableRow = document.createElement("tr");
		var nombre = document.createElement("td");
		var apellido = document.createElement("td");
		var direccion = document.createElement("td");
		var codigo = document.createElement("td");
		var ciudad = document.createElement("td");
		var hijos = document.createElement("td");
		var email = document.createElement("td");
		var eliminar = document.createElement("td");
		var eliminarBoton = document.createElement("input");

		nombre.id = "nombre";
		apellido.id = "apellido";
		direccion.id = "direccion";
		codigo.id = "codigo";
		ciudad.id = "ciudad";
		hijos.id = "hijos";
		email.id = "email";
		eliminar.id = "eliminar";

		nombre.className = "celda";
		apellido.className = "celda";
		direccion.className = "celda";
		codigo.className = "celda";
		ciudad.className = "celda";
		hijos.className = "celda";
		email.className = "celda";
		eliminar.className = "celda";

		nombre.ondblclick = function() { modificar(this); };
		apellido.ondblclick = function() { modificar(this); };
		direccion.ondblclick = function() { modificar(this); };
		codigo.ondblclick = function() { modificar(this); };
		ciudad.ondblclick = function() { modificar(this); };
		hijos.ondblclick = function() { modificar(this); };
		email.ondblclick = function() { modificar(this); };

		nombre.innerText = "nombre";
		apellido.innerText = "apellido";
		direccion.innerText = "direccion";
		codigo.innerText = "codigo";
		ciudad.innerText = "ciudad";
		hijos.innerText = "hijos";
		email.innerText = "email";


		eliminarBoton.type = "button";
		eliminarBoton.value = "Borrar fila";
		eliminarBoton.onclick = function() { borrarRegistro(req.responseText); };

		eliminar.appendChild(eliminarBoton);

		tableRow.id = req.responseText;
		tableRow.appendChild(nombre);
		tableRow.appendChild(apellido);
		tableRow.appendChild(direccion);
		tableRow.appendChild(codigo);
		tableRow.appendChild(ciudad);
		tableRow.appendChild(hijos);
		tableRow.appendChild(email);
		tableRow.appendChild(eliminar);

		tabla.appendChild(tableRow);
	}
}

function borrarRegistro(id) {
	var req = new XMLHttpRequest();

	var url = "borrarRegistro.php?registro=" + id;

	req.onload = function() { borrarRegistroVista(id, req) };

	req.open("GET", url, true);

	req.send(null);
}

function borrarRegistroVista(id, req) {

	if (req.status == 200) {

		var tableRow = document.getElementById(id);

		tableRow.parentNode.removeChild(tableRow);

	}
}