<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Dashboard</title>
</head>
<body><div class="container mt-3">
<h1 style="color: #900C3F;">Bienvenido al Dashboard, <c:out value="${user.name}" />!</h1> <p><a class="nav-link" href="/logout"><button class="btn-danger">Salir</button></a></p>
<h4>Aquí tendrás listados los Shows ingresados, podrás hacerles un REVIEW y agregar más SHOWS!</h4>
<p style="color: red;"><c:out value="${error}" /></p>
<table class="table table-striped">
	<thead>
	<tr class="table-primary">
		<th scope="col">Show</th>
		<th scope="col">Canal (Network)</th>
		<th scope="col">AVG Rating</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${shows}" var="s">
		<tr>
			<td><a href="/shows/${s.id}">${s.title}</a></td>
			<td>${s.network}</td>
			<td>${s.avgRating}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<p><a class="nav-link" href="/shows/new"><button class="btn-success">Agregar un Show</button></a></p>
</div>
</body>
</html>