<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Rating y Detalles</title>
</head>
<body><div class="container mt-3">
		<h1 style="color: #900C3F;">Detalles del Show</h1>
		<h3><c:out value="${show.title}" /></h3>
		<h5>Canal (Network): <c:out value="${show.network}" /></h5>
		<c:if test = "${show.userT.id == user.id}">
			<p><a href="/shows/${show.id}/edit"><button class="btn-success">Editar Show</button></a></p>
		</c:if>
		<hr><h3>Dale un rating!</h3>
				<p style="color: black;"><c:out value="${error}" /></p>
			    <p style="color: black;"><form:errors path="review.*"/></p>
				<form:form method="POST" action="/shows/${show.id}/review" modelAttribute="review">
			        <p>
			            <form:label path="rating">Rating:</form:label>
			            <form:select path="rating">
			            	<form:option value="5">5</form:option>
			            	<form:option value="4">4</form:option>
			            	<form:option value="3">3</form:option>
			            	<form:option value="2">2</form:option>
			            	<form:option value="1">1</form:option>
			            </form:select>
			        </p>
			        <form:input type="hidden" path="user" value="${user.id}" />
			        <form:input type="hidden" path="show" value="${show.id}" />
			        <input style="background-color: #71AB08" type="submit" value="Enviar"/>
			    </form:form>
		<h3 class="text-center">Usuarios que han Ratiado este Show</h3>
		<table class="table table-striped">
			<thead>
			<tr class="table-primary">
				<th scope="col">Nombre</th>
				<th scope="col">Rating</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${show.reviews}" var="s">
				<tr>
					<td>${s.user.name}</td>
					<td>${s.rating}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
				
			</div>
			
				
			<p><a class="nav-link" href="/shows/"><button class="btn-warning">Volver al Dashboard</button></a></p>
	    </div>
</body>
</html>