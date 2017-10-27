<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bot or Not!</title>
	
	<!-- CSS -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/yu_main.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css" />
	<link rel="stylesheet" type="text/css" href="/css/yu_loginModal.css" />
	<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet">
	<script type="text/javascript" src="/js/loadTimer.js"></script>	

</head>

<body >
	
	<div id="container">
		<!-- <div id="main_content"> -->
			<h1 class="title_stat">Welcome <span class="orange"> ${currentUser.identifier}! </span></h1>
			<p class="instructions" ><span class="orange" style="font-size: 20px"> Instructions: </span> Press "Start Game" And Guess The Bot. Try To Throw Everyone Else Off By Acting Like A Bot!</p>
			<div id="stat_panel">
				
					<div class="stat_container">
						
						<div class="center1">
							<img class="stat_logo" src="/img/logo.png" height="200px"/>
						</div>
						
						<h1 class="stats">Your Stats </h1>
						<h3 class="sDetails" >Won:  ${currentUser.wonCount}</h3>
						<h3 class="sDetails" >Lost:  ${currentUser.lossCount}</h3>
					</div>
				
			</div>
			
			
			
			<div class="orange_bg"></div>
			<input type="hidden" id="botName" value="${botName}"/>
			<input type="hidden" id="name" value="${currentUser.identifier}"/>
	
		    <div id="chat-page" class="hidden">
		    		<div class="center3">
			        <div class="chat-container">
			            <div class="chat-header">
			                <h3 class="title" style="font-size:25px"><span class="orange">BOT</span> or Not</h3>
			            </div>
			            <div class="connecting">
			                Connecting...
			            </div>
			            <ul id="messageArea">
			
			            </ul>
			            <form id="messageForm" name="messageForm" nameForm="messageForm">
			                <div class="form-group">
			                    <div class="input-group clearfix">
			                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
			                        <button type="submit" class="primary">Send</button>
			                    </div>
			                </div>
			            </form>
			        </div>
	        		</div>
	        		
	        		<div class="allUsers">
	        			<div class="chat-header">
	        				<h3 class="title" style="font-size: 25px; color: orange">Active Users</h3>
	        			</div>
	        			
					<c:forEach items="${users}" var="user" >
						<p><c:out value="${user.username}" /></p>
					</c:forEach>
			        <div id='timer'>
			        		<form method="POST" onsubmit="return false;">
						   <button onclick="clickBut()">Start Game</button>
						     <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
						   
						</form>
				    </div>
				    <div id="counter" style="show;"></div>
					<div id="timerSeconds" style="display:none;">&nbsp;seconds to go</div>
				</div>
				
				<form id="logoutForm" method="POST" action="/logout">
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				    <input class="logout" type="submit" value="Logout!" />
				</form>
			
			
	    		</div>
    		<!-- </div> -->
    		
    			<div class="modal">
	
	        		<div class="modal_close close"></div>
	           	<div class="modal_main">
	            	<img src="/img/closebtn.png" class="close" style="margin-top:13px;left:93%;position:fixed;">
	
	            	<p class="final_pick">Who's the BOT?</p>
	            	<hr>
				<form class="pick_form" action="/pick" method="POST">
					<select class="pick_inputbox" name="choice">
						<option value="0"><c:out value="${botName}" /></option>
                    		<c:forEach items="${users}" var="user" >
                        <option value="1"><c:out value="${user.nickname}" /></option>
                    		</c:forEach>
					</select>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="pick_btn" style="height: 45px" value="PICK"/>
				</form>
	            	
				</div>
			</div>
    
    

	    <script src="/js/sockjs.min.js"></script>
	    <script src="/js/stomp.min.js"></script>
	    <script src="/js/main.js"></script>
		
		
	</div>
</body>
</html>