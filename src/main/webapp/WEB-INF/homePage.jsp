<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dashboard</title>
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/css/yu_main.css" />
	<link rel="stylesheet" type="text/css" href="/css/yu_loginModal.css" />
	<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet">
</head>

<body>
	
	<div id="container">
		
		
			
		<h3 class="title" style="font-size:25px"><span class="orange">BOT</span> or Not</h3>
		<h1 class="title_stat">Welcome <span class="orange"> ${currentUser.identifier}! </span></h1>
		
		<div class="center1">
			<img class="stat_logo" src="/img/logo.png" height="200px"/>
		</div>
		
		<div class="center2">
			<div class="stat_container">

				<h1 class="stats">Your Stats </h1>
				<p class="stat_details">Lost: </p>
				<p class="stat_details">Won: </p>
				
				<button><a href="/chat">Let's Play!</a></button>
				
				<form id="logoutForm" method="POST" action="/logout">
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				    <input type="submit" value="Logout!" />
				</form>

			</div>
		</div>
		
		
		
		<div class="orange_bg"></div>
	</div>
</body>
</html>