
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="/css/yu_main.css" />
	<link rel="stylesheet" href="/css/style.css" />
	<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet">
	
	<title>Result</title>
</head>
<body>
	<h1 class="result_title"><c:out value="${ result }"/></h1>

	<img class="result_logo" src="/img/logo.png" />


	<form class="logoutt" id="logoutForm" method="POST" action="/logout">
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    <input class="logout_btn" type="submit" value="Logout!" />
	</form>

</body>
</html>