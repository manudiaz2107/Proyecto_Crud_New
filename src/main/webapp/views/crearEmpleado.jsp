<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Crear Nuevo Empleado</title>
<style>
        /* Estilos Generales */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #eaf0f1;
            margin: 0;
            padding: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #2C3E50;
            font-size: 2.5em;
            margin-bottom: 30px;
            text-align: center;
        }

        /* Error Message Style */
        .error {
            color: #ff4d4d;
            margin-bottom: 20px;
        }

        form {
            width: 70%;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
            border: 2px solid #A3D9A5;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #2C3E50;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            padding: 15px 20px;
            background-color: #A3D9A5;
            color: #2C3E50;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
            width: 100%;
            text-transform: uppercase;
        }

        input[type="submit"]:hover {
            background-color: #86C98D;
            transform: translateY(-3px);
        }

        a {
            display: block;
            width: 70%;
            padding: 15px 20px;
            margin-bottom: 20px;
            background-color: #A3D9A5;
            color: #2C3E50;
            text-align: center;
            text-decoration: none;
            border-radius: 10px;
            border: 2px solid #A3D9A5;
            transition: background-color 0.3s ease, transform 0.3s ease, border 0.3s ease;
        }

        a:hover {
            background-color: #86C98D;
            transform: translateY(-3px);
            border-color: #86C98D;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            h1 {
                font-size: 2em;
            }

            form,
            a {
                width: 90%;
            }
        }
    </style>
</head>
<body>
	<h1>Crear Nuevo Empleado</h1>

	<c:if test="${not empty error}">
		<div style="color: red;">${error}</div>
	</c:if>

	<br>
	<a href="javascript:history. back()">Volver</a>
	<form action="${pageContext.request.contextPath}/crearEmpleado"
		method="post">
		<label>DNI:</label> <input type="text" name="dni" required /><br />

		<label>Nombre:</label> <input type="text" name="nombre" required /><br />

		<label>Sexo:</label> <select name="sexo" required>
			<option value="M">Masculino</option>
			<option value="F">Femenino</option>
		</select><br /> <label>Categoría:</label> <select name="categoria" required>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>

		</select><br /> <label>Años Trabajados:</label> <input type="number"
			name="anos_trabajados" required /><br /> <input type="submit"
			value="Crear Empleado" />
	</form>

	<br>
	<form action="../index.jsp" method="get">
		<input type="submit" value="Volver al Menú Principal">
	</form>
</body>
</html>
