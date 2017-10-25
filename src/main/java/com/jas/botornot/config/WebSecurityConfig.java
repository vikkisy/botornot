package com.jas.botornot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.jas.botornot.models.ActiveUserStore;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    

    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }
    
    @Autowired
    MySimpleUrlAuthenticationSuccessHandler handler;
    
    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;
    
    
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
        
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
   
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers("/static/**", "/registration").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
            		.successHandler(handler)
            		.loginPage("/login")
                .permitAll()
                .and()
            .logout()
            		.logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
    }
    
    // 1
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }     
}
