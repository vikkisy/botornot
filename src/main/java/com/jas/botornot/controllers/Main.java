package com.jas.botornot.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jas.botornot.models.Role;
import com.jas.botornot.models.User;
import com.jas.botornot.services.UserService;
import com.jas.botornot.validator.UserValidator;


@Controller
public class Main {
    private UserService userService;
    // NEW
    private UserValidator userValidator;
    
    // NEW
    public Main(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "redirect:/login";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "loginPage";
        }
        if(userService.findAdmins().isEmpty()) {
            userService.saveUserWithAdminRole(user);
        }
        else {
        		if(userService.findByUsername(user.getUsername()) == null) {
        			userService.saveWithUserRole(user);
        		}
        		else {
        			model.addAttribute("errorMessage", "Email already in use.");
        			return "loginPage";
        		}
        }
        return "redirect:/dashboard";
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        User user = new User();
        model.addAttribute("user", user);
    		if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
        		
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "loginPage";
    }
    @RequestMapping(value = {"/dashboard", "/home", "/"})
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        User current = userService.findByUsername(username);
        if(userService.findAdmins().contains(current)) {
        		return "redirect:/admin";
        }
        else {
            model.addAttribute("currentUser", current);
            return "homePage";
        }

    }
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
    		List<User> users = userService.findUsers();
    		List<User> admins = userService.findAdmins();
        String username = principal.getName();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", userService.findByUsername(username));
        model.addAttribute("admins", admins);

        return "adminPage";
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
    		userService.deleteUser(id);
        return "redirect:/admin";
    }
    @RequestMapping("/makeadmin/{id}")
    public String makeAdmin(@PathVariable("id") Long id) {
    		userService.makeAdmin(id);
        return "redirect:/admin";
    }
}