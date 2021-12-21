<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ca">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap"
	rel="stylesheet">
<title>Crear libro</title>
</head>
<body class=""
	style="font-family: 'Montserrat', sans-serif; background-color: #2A324B;">

	<!--  
		AMARILLO: background-color: #E8C547;
		1 OSCURO: background-color: #2A324B;
		2 OSCURO: background-color: #767B91;
		3 OSCURO: background-color: #C7CCDB;
		4 OSCURO: background-color: #E1E5EE;
	-->
	<header>
		<div class="w-full p-8" style="background-color: #E8C547;">
			<div>
				<h1 class="text-4xl font-bold mb-8 tracking-widest">Creación de
					libro</h1>
				<p class="text-2xl">Introduce los valores para crear el libro.</p>
			</div>
		</div>
	</header>
	<main class="">
		<div class="w-6/12 mx-auto mt-6 p-6 rounded-xl"
			style="background-color: #C7CCDB">
			<form:form modelAttribute="libro">
				<div class="flex flex-col mb-4">
					<label for="isbn"
						class="w-max border-b-2 border-gray-500 mb-2 text-lg tracking-wider">
						Introduce el ISBN del libro aquí: </label>
					<form:input id="isbn" path="isbn" type="text"
						class="w-6/12 p-2 rounded-md hover:bg-yellow-200 focus:bg-yellow-200 duration-500"
						placeholder="Introduce ISBN"/>
				</div>
				<div class="flex flex-col mb-4">
					<label for="author"
						class="w-max border-b-2 border-gray-500 mb-2 text-lg tracking-wider"
						for="author"> Introduce el autor aquí: </label>

					<form:input id="author" path="author" type="text"
						class="w-6/12 p-2 rounded-md hover:bg-yellow-200 focus:bg-yellow-200 duration-500"
						placeholder="Introduce autor" />
				</div>
				<div class="flex flex-col mb-4">
					<label for="title"
						class="w-max border-b-2 border-gray-500 mb-2 text-lg tracking-wider"
						for="title"> Introduce el título aquí: </label>

					<form:input id="title" path="title" type="text"
						class="w-6/12 p-2 rounded-md hover:bg-yellow-200 focus:bg-yellow-200 duration-500"
						placeholder="Introduce título" />
				</div>

				<input
					class="block w-max mx-auto p-2 cursor-pointer rounded-md hover:bg-green-300 duration-500"
					type="submit" value="Crear libro">
			</form:form>
		</div>
	</main>
</body>
</html>
