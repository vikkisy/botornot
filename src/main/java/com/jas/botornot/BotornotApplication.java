package com.jas.botornot;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jas.botornot.cleverbotapi.CleverBotQuery;

@SpringBootApplication
public class BotornotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotornotApplication.class, args);
//		Scanner keyboard;
//	       CleverBotQuery botQuery;
//	       String input;
//	       boolean done;
// 
//	      keyboard = new Scanner(System.in);
//
//	       do
//	       {
//	           System.out.print("Enter your message: ");
//	           input = keyboard.nextLine();
//	           done = input.equals("done");
//
//	           if (!done)
//	           {
//	               botQuery = new CleverBotQuery("CC53v1X1jFOtuG0vKA3QDL7nfkw", input);
//	               try
//	               {
//	                   botQuery.sendRequest();
//	                   System.out.println("BOT: " + botQuery.getResponse());
//	               }
//	               catch (IOException e)
//	               {
//	                   System.out.println(e.getMessage());
//	               }
//	           }
//	       } while(!done);

	   }
	}