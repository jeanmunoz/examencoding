<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Crear un nuevo Show</title>
</head>
<body>

	
	<div class="container mt-3">
		<h1 style="color: #900C3F;">Crear un nuevo Show</h1>
		<h3>Título y Canal (Network) son obligatorios</h3>
		<!-- All errors displayed here if result from @Valid BindResult in controls returns errors -->
	    <p style="color: orange;"><form:errors path="show.*"/></p>
		<!-- FORM TO CREATE A NEW SHOW -->
		<form:form method="POST" action="/shows" modelAttribute="show">
	        <p>
	            <form:label path="title">Titulo:</form:label>
	            <form:input type="text" path="title"/>
	        </p>
	        <p>
	            <form:label path="network">Canal (Network):</form:label>
	            <form:input type="text" path="network"/>
	        </p>
	        <form:input type="hidden" path="userT" value="${user.id}"/>
	        <input style="background-color: #71AB08" type="submit" value="Agregar nuevo Show"/>
	    </form:form>
	    <p><a class="nav-link" href="/shows/"><button class="btn-warning">Volver al Dashboard</button></a></p>
    </div>

</body>
</html>