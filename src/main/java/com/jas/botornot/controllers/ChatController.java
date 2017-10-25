package com.jas.botornot.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.jas.botornot.cleverbotapi.CleverBotQuery;
import com.jas.botornot.models.ChatMessage;
import com.jas.botornot.models.User;

@Controller
public class ChatController {
	CleverBotQuery botQuery;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    		botQuery = new CleverBotQuery("CC53v1X1jFOtuG0vKA3QDL7nfkw", chatMessage.getContent());
        try
        {
            botQuery.sendRequest();
            System.out.println("BOT: " + botQuery.getResponse());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }

}