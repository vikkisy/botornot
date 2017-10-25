package com.jas.botornot.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.jas.botornot.services.UserService;

@Component("myLogoutSuccessHandler")
@Lazy
public class MyLogoutSuccessHandler implements LogoutSuccessHandler{
	private UserService userService;
	
	public MyLogoutSuccessHandler(UserService userService) {
		this.userService = userService;
	}
    @Override
    public void onLogoutSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
		userService.saveCurDate(userService.findByUsername(authentication.getName()));
		HttpSession session = request.getSession();
        if (session != null){
            session.removeAttribute("user");
        }
		response.setStatus(HttpServletResponse.SC_OK);
		//redirect to login
		response.sendRedirect("/login");
    }
}