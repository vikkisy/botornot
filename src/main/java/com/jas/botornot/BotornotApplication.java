package com.jas.botornot;

import java.io.IOException;
import java.util.Scanner;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

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
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(9090);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        tomcat.addAdditionalTomcatConnectors(ajpConnector);
        return tomcat;
	}
}