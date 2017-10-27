package com.jas.botornot.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jas.botornot.controllers.LoggedUser;
import com.jas.botornot.models.ActiveUserStore;
import com.jas.botornot.models.User;
import com.jas.botornot.services.UserService;

@Component("myAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Autowired
    ActiveUserStore activeUserStore;
    
    @Autowired
    UserService userService;
    
    private List<String> names = Arrays.asList("Sheila",
    		"Deana", "Winford", "Janean", "William", "Eugena", "Morris", "Kimber", "Tresa", "Gregorio", "Jacki", "Nakesha", "Catherina", "Timothy", "Carlotta", "Peggie", "Arnoldo", "Nickolas", "Antonetta", "Candyce", "Tupac", "Biggie", "Jay-Z", "Beyonce", "Bieber", "2Chainz", "CardiB", "Dicky", "Pump", "Miley", "Katy", "Kanye", "Yeezy", "Sean", "Selena", "Future", "Taylor", "Sheeran", "Eminem", "Drake", "Yachty", "Nate", "JCole", "Dram", "Logic", "Calvin", "Rihanna", "Khalid", "Alessia", "Kendrick", "Desiigner", "Uzi", "Kodak", "Jeremih", "Nicki", "Remy", "Nicole", "Jessie", "Gaga", "Demi", "Hailee", "JLo", "MaryJ");
    
    Random rand = new Random();
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) 
      throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
        		User curUser = userService.findByUsername(authentication.getName());
            curUser.setNickname(names.get(rand.nextInt(names.size())));
            userService.update(curUser);
            LoggedUser user = new LoggedUser(curUser.getIdentifier(), curUser.getNickname(), activeUserStore);
            session.setAttribute("user", user);
        }
		response.setStatus(HttpServletResponse.SC_OK);
		//redirect to login
		response.sendRedirect("/");
    }
}