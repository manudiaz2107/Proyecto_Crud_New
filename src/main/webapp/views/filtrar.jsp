<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="es">

<head>

<title>Filtrar Empleados</title>
<style>
/* Estilo General */
body {
	font-family: 'Arial', sans-serif;
	background-color: #e9f7f1; /* Fondo verde pastel */
	margin: 0;
	padding: 20px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

h2 {
	color: #2e8b57; /* Verde bosque */
	font-size: 2.5em;
	margin-bottom: 20px;
}

a {
	color: #2e8b57;
	text-decoration: none;
	margin-top: 20px;
	font-size: 1.1em;
}

a:hover {
	text-decoration: underline;
}

form {
	width: 60%;
	background-color: #ffffff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
	margin-bottom: 30px;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #4caf50; /* Verde más brillante */
	font-size: 1.1em;
}

input[type="text"], input[type="number"], select {
	width: 100%;
	padding: 12px;
	border: 1px solid #4caf50; /* Borde verde brillante */
	border-radius: 6px;
	font-size: 1em;
	margin-bottom: 20px;
}

button[type="submit"], button[type="reset"] {
	padding: 12px 20px;
	background-color: #66bb6a; /* Verde más suave */
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background-color 0.3s ease, transform 0.3s ease;
	margin: 10px;
	display: inline-block;
	text-decoration: none;
}

button[type="submit"]:hover, button[type="reset"]:hover {
	background-color: #388e3c; /* Verde más oscuro */
	transform: translateY(-2px);
}

button a {
	color: white;
	text-decoration: none;
}

button a:hover {
	text-decoration: underline;
}

/* Tabla */
table {
	width: 80%;
	border-collapse: collapse;
	margin-bottom: 30px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px;
	text-align: center;
	border: 1px solid #e0e0e0;
}

th {
	background-color: #66bb6a; /* Verde suave */
	color: white;
	font-size: 1.2em;
}

tr:nth-child(even) {
	background-color: #f1f8e9; /* Fondo verde claro para filas pares */
}

tr:hover {
	background-color: #c8e6c9; /* Verde más suave al pasar el ratón */
}

/* Diseño Adaptable */
@media (max-width: 768px) {
	form, table {
		width: 90%;
	}
	h2 {
		font-size: 2em;
	}
	label {
		font-size: 1em;
	}
}
</style>

</head>

<body>

	<h2>Filtrar Empleados</h2>
	<!-- Formulario de Filtrado -->

	<form action="filtrar" method="get">

		<label for="sexo">Sexo:</label> 
		<select id="sexo" name="sexo">
			<option value="">--Selecciona--</option>
			<option value="M" ${param.sexo == 'M' ? 'selected' : ''}>Masculino</option>
			<option value="F" ${param.sexo == 'F' ? 'selected' : ''}>Femenino</option>
		</select>

		<label for="nombre">Nombre:</label> 
		<input type="text" id="nombre" name="nombre" value="${param.nombre}">

		<label for="categoria">Categoría:</label>
		<select id="categoria" name="categoria">
			<option value="">--Selecciona--</option>
			<c:forEach var="i" begin="1" end="10">
				<option value="${i}" ${param.categoria == i ? 'selected' : ''}>${i}</option>
			</c:forEach>
		</select>

		<label for="anyos">Años:</label> 
		<input type="number" id="anyos" name="anyos" value="${param.anyos}">

		<button type="submit">Apply</button>
		<button type="submit">
			<a href="filtrar">Reset</a>
		</button>

	</form>

	<!-- Mostrar Lista de Empleados Filtrados -->

	<h3>Resultados del Filtrado:</h3>

	<table border="1">
		<thead>
			<tr>
				<th>Dni</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="empleado" items="${empleados}">
				<tr>
					<td>${empleado.dni}</td>
					<td>${empleado.nombre}</td>
					<td>${empleado.sexo}</td>
					<td>${empleado.categoria}</td>
					<td>${empleado.anosTrabajados}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="index.jsp">Volver al inicio</a>
	<a href="javascript:history.back()">Volver</a>

</body>

</html>
