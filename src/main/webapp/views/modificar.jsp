<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Empleado"%>

<html>
<head>
<title>Modificar Empleado</title>
<style>
        /* Estilos Generales */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #141414; /* Fondo oscuro */
            color: #E0E0E0; /* Texto gris claro */
            margin: 0;
            padding: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #00E676; /* Título en verde neón */
            font-size: 2.5em;
            margin-bottom: 30px;
        }

        .error {
            color: #FF5252; /* Rojo para mensajes de error */
            margin-bottom: 20px;
        }

        /* Formulario */
        form {
            background-color: #1E1E1E; /* Fondo más oscuro para el formulario */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);
            width: 350px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #00E676; /* Verde para etiquetas */
        }

        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            background-color: #2C2C2C;
            border: 1px solid #444;
            border-radius: 5px;
            color: #E0E0E0;
        }

        input[type="submit"] {
            display: inline-block;
            width: 100%;
            padding: 12px;
            background-color: #00E676; /* Verde neón */
            color: #141414;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #00C853; /* Verde más oscuro */
            transform: translateY(-3px);
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #00B0FF; /* Azul neón */
            text-decoration: none;
            border: 1px solid #00B0FF;
            border-radius: 5px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        a:hover {
            background-color: #00B0FF; /* Fondo azul al pasar */
            color: #141414;
        }

        /* Diseño Responsivo */
        @media (max-width: 600px) {
            h1 {
                font-size: 2em;
            }
            form {
                width: 100%;
            }
        }
    </style>
</head>
<body>
	<h1>Modificar Empleado</h1>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>

	<c:if test="${not empty empleado}">
		<form action="modificar" method="post">
			<input type="hidden" name="dni" value="${empleado.dni}" /> <label>Nombre:</label>
			<input type="text" name="nombre" value="${empleado.nombre}" required /><br />
			<label>Sexo:</label> <select name="sexo" required>
				<option value="M"
					<c:if test="${empleado.sexo == 'M'}">selected</c:if>>Masculino</option>
				<option value="F"
					<c:if test="${empleado.sexo == 'F'}">selected</c:if>>Femenino</option>
			</select><br /> <label>Categoría:</label> <select name="categoria" required>
				<option value="1"
					<c:if test="${empleado.categoria == '1'}">selected</c:if>>1</option>
				<option value="2"
					<c:if test="${empleado.categoria == '2'}">selected</c:if>>2</option>
				<option value="3"
					<c:if test="${empleado.categoria == '3'}">selected</c:if>>3</option>
				<option value="4"
					<c:if test="${empleado.categoria == '4'}">selected</c:if>>4</option>
				<option value="5"
					<c:if test="${empleado.categoria == '5'}">selected</c:if>>5</option>
				<option value="6"
					<c:if test="${empleado.categoria == '6'}">selected</c:if>>6</option>
				<option value="7"
					<c:if test="${empleado.categoria == '7'}">selected</c:if>>7</option>
				<option value="8"
					<c:if test="${empleado.categoria == '8'}">selected</c:if>>8</option>
				<option value="9"
					<c:if test="${empleado.categoria == '9'}">selected</c:if>>9</option>
				<option value="10"
					<c:if test="${empleado.categoria == '10'}">selected</c:if>>10</option>
			</select><br /> <label>Años Trabajados:</label> <input type="number"
				name="anos_trabajados" value="${empleado.anosTrabajados}" required /><br />
			<input type="submit" value="Modificar" />
		</form>
	</c:if>

	<br>
	<a href="index.jsp">Volver al Menu</a>
	<a href="javascript:history. back()">Volver</a>
</body>
</html>
