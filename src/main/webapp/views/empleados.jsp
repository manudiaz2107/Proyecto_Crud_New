<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Empleado"%>
<html>

<head>
<title>Lista de Empleados</title>
<style>
/* Estilos Generales */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 40px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

h2 {
	color: #2C3E50;
	font-size: 2.5em;
	margin-bottom: 30px;
	text-align: center;
}

/* Tabla */
table {
	width: 80%;
	border-collapse: collapse;
	margin-bottom: 30px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

th {
	background-color: #A3D9A5; /* Verde claro */
	color: white;
}

tr:nth-child(even) {
	background-color: #ecf0f1;
}

tr:hover {
	background-color: #A3D9A5; /* Verde al pasar el ratón */
	color: white;
}

/* Estilos para los formularios de los botones */
input[type="submit"], a {
	display: inline-block;
	padding: 12px 20px;
	background-color: #A3D9A5; /* Verde claro */
	color: #2C3E50;
	text-align: center;
	text-decoration: none;
	border-radius: 8px;
	border: none;
	transition: background-color 0.3s ease, transform 0.3s ease;
	margin: 5px 0;
}

input[type="submit"]:hover, a:hover {
	background-color: #86C98D; /* Verde más oscuro */
	transform: translateY(-2px);
}

a {
	width: auto;
	cursor: pointer;
}

/* Diseño Responsivo */
@media (max-width: 600px) {
	h2 {
		font-size: 2em;
	}
	table {
		width: 100%;
	}
}
</style>
</head>
<body>
	<h2>Lista de Empleados</h2>
	<form action="views/crearEmpleado.jsp" method="get">
		<input type="submit" value="Agregar Nuevo Empleado">
	</form>

	<table border="1">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años Trabajados</th>
				<th>Acciones</th>
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
					<td>
						<form action="modificar" method="get">
							<input type="hidden" name="dni" value="${empleado.dni}" /> <input
								type="submit" value="Modificar">
						</form>
						<form action="eliminarEmpleServlet" method="post"
							style="display: inline;">
							<input type="hidden" name="dni" value="${empleado.dni}" /> <input
								type="submit" value="Eliminar"
								onclick="return confirm('¿Estás seguro de que deseas eliminar este empleado?');">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<a href="index.jsp">Volver al menu</a>
	<br>
	<a href="javascript:history. back()">Volver</a>

</body>
</html>
