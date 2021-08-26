<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
    
<!DOCTYPE html>
<html>
<head>
    
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Inicio</title>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<span class="border border-primary"><div class="col-xs-6 col-md-6">
			
				<h1 style="color: #900C3F;">Registro</h1>
			    <p style="color: orange;"><form:errors path="user.*"/></p>
			    
			    <form:form   method="POST" action="/users" modelAttribute="user">
			        <p>
			            <form:label path="name">Nombre:</form:label>
			            <form:input type="text" path="name"/>
			        </p>
			        <p>
			            <form:label path="email">Email:</form:label>
			            <form:input type="email" path="email"/>
			        </p>
			        <p>
			            <form:label path="password">Password:</form:label>
			            <form:password path="password"/>
			        </p>
			        <p>
			            <form:label path="passwordConfirmation">Confirmacion de Password :</form:label>
			            <form:password path="passwordConfirmation"/>
			        </p>
			        <input style="background-color: #71AB08" type="submit" value="Registrarse"/><br><br>
			    </form:form>
			</div></span>
		
		<p style="color: #ffffff;">---------------------------</p>
			<span class="border border-primary"><div class="col-xs-6 col-md-6">
			    <h1 style="color: #900C3F;">Login</h1>
			    <p style="color: orange;"><c:out value="${error}" /></p>
			    
			    <form method="post" action="/login">
			        <p>
			            <label for="email">Email</label>
			            <input type="text" id="email" name="email"/>
			        </p>
			        <p>
			            <label for="password">Password</label>
			            <input type="password" id="password" name="password"/>
			        </p>
			        <input style="background-color: #71AB08" type="submit" value="Loguearse"/>
			    </form>
			</div></span>
		</div>
    </div>

</body>
</html>