package com.jas.botornot.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.jas.botornot.cleverbotapi.CleverBotQuery;
import com.jas.botornot.models.ActiveUserStore;
import com.jas.botornot.models.ChatMessage;
import com.jas.botornot.models.User;
import com.jas.botornot.services.UserService;

@Controller
public class ChatController {
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	CleverBotQuery botQuery;
	
    @Autowired
    ActiveUserStore activeUserStore;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    		chatMessage.setId(activeUserStore.getUsers().indexOf(chatMessage.getSender())+1);
    		sendMessageBot(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }
    @SendTo("/channel/public")
    public ChatMessage sendMessageBot(@Payload ChatMessage chatMessage) {
    		
    		botQuery = new CleverBotQuery("CC53v1X1jFOtuG0vKA3QDL7nfkw", chatMessage.getContent());
    		new java.util.Timer().schedule( 
	        new java.util.TimerTask() {
	            @Override
	            public void run() {
	            	try
	                {
	                    botQuery.sendRequest();
	                    ChatMessage newChat = new ChatMessage();
	                    newChat.setContent(botQuery.getResponse());
	                    newChat.setSender("CleverBot");
	                    messagingTemplate.convertAndSend("/channel/public", newChat);
	                }
	                catch (IOException e)
	                {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }, 
	        1500 
    		);
        return chatMessage;
    }
}