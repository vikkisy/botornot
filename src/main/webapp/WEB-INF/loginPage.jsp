<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login Page</title>
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/css/yu_loginModal.css" />
	<link rel="stylesheet" type="text/css" href="/css/yu_main.css" />
	<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet">
	
	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/js/yu_loginModal.js"></script>
	<script type="text/javascript" src="/js/yu_main.js"></script>
</head>
<body>
	<div id="container">
	
		<c:if test="${logoutMessage != null}">
	        <c:out value="${logoutMessage}"></c:out>
	    </c:if>
	    
		<h1 class="title"><span class="orangee">BOT</span> or NOT</h1>
		
		<div class="page-front">
			<img src="/img/logo.png" class="front_logo" />
		</div>
		<div class="page-back">
			<img src="/img/logo_shadow.png" class="front_logo" />
		</div>
		
		
		
		
		
		<!-- <button class="call_modal" style="cursor:pointer;">LOGIN</button> -->
	    		<div class="modal">
	
	        		<div class="modal_close close"></div>
	           	<div class="modal_main">
	            	<img src="/img/closebtn.png" class="close" style="margin-top:13px;left:93%;position:fixed;">
	
	            	<p class="log_p">LOGIN</p>
	            	<hr>
				<!-- ADD LOGIN FORM -->
	 	    		
	 	    		<form class="form" method="POST" action="/login">
	            		<table>
	                		<tr>
	                			<td><input type="text" id="username" name="username" placeholder="Email" /></td>
	                		</tr>
	                		<tr>
	                			<td><input type="password" id="password" name="password" placeholder="Password" /></td>
	                		</tr>
	                		<tr>
	                			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	                			<td><input class="button" type="submit" value="Login" /></td>
	                		</tr>
	                	</table>
	            </form>
	                	
	            	<c:if test="${errorMessage != null}">
	     			<p class="errors"><c:out value="${errorMessage}"></c:out></p>
	 			</c:if>
	 	    		
				<hr>
	            	<p class="log_acc">Don't have an account? </p>
				<a class="reg_change" style="cursor:pointer;"><span class="orange">Register Here</span></a>
	
				</div>
			</div>
	
	        <!-- Register MODAL -->
	
	         <div class="reg_modal">
	             <div class="modal_close close"></div>
	             <div class="reg_modal_main">
	                	<img src="/img/closebtn.png" class="reg_close" style="margin-top:13px;left:93%;position:fixed;">
	
	                 <p class="log_p">REGISTER</p>
	                 <hr>
					<!-- ADD REGISTER FORM -->
	                	
	                	<form:form method="POST" action="/registration" modelAttribute="user" class="form">
	                		<table>
	                			<tr>
	                				<td><form:input path="identifier" placeholder="Username"/></td>
	                			</tr>
	                			<tr>
	                				<td><form:input path="username" placeholder="Email"/></td>
	                			</tr>
	                			<tr>
	                				<td><form:input type="password" path="password" placeholder="Password"/></td>
	                			</tr>
	                			<tr>
	                				<td><form:input type="password" path="passwordConf" placeholder="Confirm Password"/></td>
	                			</tr>
	                			<tr>
	                				<td><input class="button" type="submit" value="Register"/></td>
	                			</tr>
	                		</table>
	                	</form:form>
	
	             </div>
			</div>
		</div>























</body>
</html>