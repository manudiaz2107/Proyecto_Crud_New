<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<style>
</style>
<head>
<title>Aplicación de Nóminas</title>
<style>
/* Estilos Generales */
body {
	font-family: 'Arial', sans-serif;
	background-color: #eaf0f1; /* Fondo suave y claro */
	margin: 0;
	padding: 40px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh; /* Centra todo en la pantalla */
}

h1 {
	color: #2C3E50; /* Título en color oscuro para contraste */
	font-size: 2.5em;
	margin-bottom: 30px;
	text-align: center;
}

a {
	display: block;
	width: 70%;
	padding: 15px 20px;
	margin-bottom: 20px;
	background-color: #A3D9A5; /* Verde pastel */
	color: #2C3E50; /* Texto oscuro para mayor legibilidad */
	text-align: center;
	text-decoration: none;
	border-radius: 10px;
	border: 2px solid #A3D9A5; /* Borde verde que coincide con el fondo */
	transition: background-color 0.3s ease, transform 0.3s ease, border 0.3s ease;
}

a:hover {
	background-color: #86C98D; /* Verde más oscuro al pasar el ratón */
	transform: translateY(-3px);
	border-color: #86C98D; /* Cambio de borde al hover */
}

/* Diseño para pantallas pequeñas */
@media (max-width: 600px) {
	h1 {
		font-size: 2em; /* Reduce el tamaño del título en pantallas pequeñas */
	}
	a {
		width: 90%; /* Hace los botones más anchos en dispositivos pequeños */
	}
}
</style>

</head>
<body>
	<h1>Bienvenido a la Aplicación de Nóminas</h1>


	<a href="front?option=empleados">Listar empleados</a>
	<a href="front?option=salario">Mostrar salario de un empleado</a>
	<a href="front?option=filtrar">Filtrar</a>

</body>
</html>
