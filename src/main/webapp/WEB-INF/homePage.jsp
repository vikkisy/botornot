<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
</head>
<style>
#box{
border: 1px solid black;
width: 300px;
}

</style>
<body>

<h1>Welcome ${currentUser.identifier}!</h1>

<button><a href="/chat">Let's Play!</a></button>

<div id="box">
<P>Your Stats </P>
<P>Lost: </P>
<P>Won: </P>
</div>
<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</body>
</html>