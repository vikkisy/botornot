<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat</title>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
</body>
<c:out value="${currentUser.identifier}" />
<input type="hidden" id="name" value="${currentUser.identifier}"/>

    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>Spring WebSocket Chat Demo</h2>
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

    <script src="/js/sockjs.min.js"></script>
    <script src="/js/stomp.min.js"></script>
    <script src="/js/main.js"></script>
  </body>
</html>