<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Editar Show</title>
</head>
<body>

	<div class="container mt-3">
		<h1 style="color: #900C3F;">Editar: ${show.title}</h1>
		<h3>Título y Canal (Network) son obligatorios, no puede dejarlos en blanco</h3>
	    <p style="color: orange;"><form:errors path="show.*"/></p>
		<form:form method="POST" action="/shows/${show.id}/update" modelAttribute="show">
	        <p>
	            <form:label path="title">Title:</form:label>
	            <form:input type="text" path="title" />
	        </p>
	        <p>
	            <form:label path="network">Canal (Network):</form:label>
	            <form:input type="text" path="network" />
	        </p>
     	    <form:input type="hidden" path="avgRating" value="${show.avgRating}"/>
	        <form:input type="hidden" path="userT" value="${user.id}"/>
	        <input style="background-color: #71AB08" type="submit" value="Actualizar Show"/> <a href="/shows/${show.id}"><button class="btn-warning" type="button">Volver al Show</button></a>
	    </form:form>
	    <br>
	    <form action="/shows/delete/${show.id}" method="post">
	    	<input type="hidden" name="_method" value="delete">
	    	<input style="background-color: red" type="submit" value="Borrar Show">
		</form> 
	    <p><a class="nav-link" href="/shows/"><button class="btn-warning">Volver al Dashboard</button></a></p>
    </div>

</body>
</html>