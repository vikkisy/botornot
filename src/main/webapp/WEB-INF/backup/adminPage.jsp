<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dash</title>
</head>
<body>
<h1>Welcome ${currentUser.identifier}!</h1>
<table class="table">
<tr>
	<th>Name</th>
	<th>Email</th>
	<th>Action</th>
<tr>  
<c:forEach items="${users}" var="user">
  
    <td><c:out value="${user.identifier}"/></td>
    <td><c:out value="${user.username}"/></td>
    <td><a href="/delete/${user.id}">delete</a> <a href="/makeadmin/${user.id}">make-admin</a></td>
</tr>
</c:forEach>

<c:forEach items="${admins}" var="admin">
  
    <td><c:out value="${admin.identifier}"/></td>
    <td><c:out value="${admin.username}"/></td>
    <td>admin</td>
</tr>
</c:forEach>
</table>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</body>
</html>