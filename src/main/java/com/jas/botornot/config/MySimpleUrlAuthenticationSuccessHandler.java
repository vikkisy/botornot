package com.jas.botornot.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jas.botornot.controllers.LoggedUser;
import com.jas.botornot.models.ActiveUserStore;

@Component("myAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Autowired
    ActiveUserStore activeUserStore;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) 
      throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
            session.setAttribute("user", user);
        }
		response.setStatus(HttpServletResponse.SC_OK);
		//redirect to login
		response.sendRedirect("/");
    }
}