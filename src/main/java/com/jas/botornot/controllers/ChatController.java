package com.jas.botornot.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
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
    
    UserService userService;
    
    public ChatController(UserService userService) {
    		this.userService = userService;
    }
    
    private List<String> names = Arrays.asList("Sheila",
    		"Deana", "Winford", "Janean", "William", "Eugena", "Morris", "Kimber", "Tresa", "Gregorio", "Jacki", "Nakesha", "Catherina", "Timothy", "Carlotta", "Peggie", "Arnoldo", "Nickolas", "Antonetta", "Candyce", "Tupac", "Biggie", "Jay-Z", "Beyonce", "Bieber", "2Chainz", "CardiB", "Dicky", "Pump", "Miley", "Katy", "Kanye", "Yeezy", "Sean", "Selena", "Future", "Taylor", "Sheeran", "Eminem", "Drake", "Yachty", "Nate", "JCole", "Dram", "Logic", "Calvin", "Rihanna", "Khalid", "Alessia", "Kendrick", "Desiigner", "Uzi", "Kodak", "Jeremih", "Nicki", "Remy", "Nicole", "Jessie", "Gaga", "Demi", "Hailee", "JLo", "MaryJ");
    Random rand = new Random();


    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
    		String username = principal.getName();
    		User current = userService.findByUsername(username);
    		chatMessage.setId(activeUserStore.getUsers().indexOf(chatMessage.getSender())+1);
    		chatMessage.setSender(names.get(rand.nextInt(names.size())));
    		chatMessage.setSenderName(current.getNickname());
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