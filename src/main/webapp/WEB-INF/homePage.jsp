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

<h1>Welcome ${currentUser.firstName}!</h1>
<div id="box">
<P>First Name: ${currentUser.firstName}</P>
<P>Last Name: ${currentUser.lastName}</P>
<P>Email: ${currentUser.username}</P>
<P>Sign up date: ${currentUser.createdAt}</P>
<P>Last Sign in: ${currentUser.updatedAt}</P>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</div>
</body>
</html>