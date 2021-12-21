<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Libros</title>
</head>
<body class="box-border m-0 p-0"
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
				<h1 class="text-4xl font-bold mb-8 tracking-widest">Nuestros
					libros</h1>
				<p class="text-2xl">Aquí tienes todos los libros que disponemos
					para comprar.</p>
			</div>
		</div>
	</header>
	<main class="">
		<div class="w-max mx-auto">
			<table
				class="w-max border-collapse border-2 border-black table-auto mx-auto mt-4"
				style="background-color: #C7CCDB;">
				<tr class="border" style="background-color: #767B91;">
					<td class="p-4">ISBN</td>
					<td class="p-4">Autor</td>
					<td class="p-4">Título</td>
					<td class="p-4">Actualizar libro</td>
					<td class="p-4">Borrar libro</td>
				</tr>
				<c:forEach items="${libros}" var="libro">
					<tr>
						<td class="p-4 w-max">${libro.isbn}</td>
						<td class="p-4 w-max">${libro.author}</td>
						<td class="p-4 w-max">${libro.title}</td>
						<td class="p-4 w-max"><spring:url var="updateUrl"
								value='/clientUpdateBook'>
								<spring:param name="isbn" value="${libro.isbn}" />
							</spring:url> <a href="${updateUrl}"
							class="p-2 bg-yellow-200 rounded-md hover:bg-yellow-500 duration-500">
								Actualizar </a></td>
						<td class="p-4"><a
							href="<spring:url value='/clientDeleteBook?isbn=${libro.isbn}' />"
							class="p-2 bg-red-200 rounded-md hover:bg-red-500 duration-500">
								Borrar </a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="w-6/12 p-4 rounded-md mx-auto mt-12 ring ring-gray-700" style="background-color: #767B91;">
			<h1 class="text-xl border-b-2 border-black w-max mb-4"> Actualizar libro </h1>
			<p class="text-md tracking-wide mb-6">
				Aquí puede añadir un nuevo libro al listado.
			</p>
			<spring:url var="createUrl"	value='/clientCreateBook'/>
			<a href="${createUrl}"
				class="block w-max p-4 mx-auto bg-blue-200 rounded-md hover:bg-blue-300 ring-2 duration-500">
				Añadir un nuevo libro </a>
		</div>
	</main>
</body>
</html>
