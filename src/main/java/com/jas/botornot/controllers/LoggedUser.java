package com.jas.botornot.controllers;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import com.jas.botornot.models.ActiveUserStore;
import com.jas.botornot.models.User;

@Component
public class LoggedUser implements HttpSessionBindingListener {
 
    private String username; 
    private String nickname;
    private ActiveUserStore activeUserStore;
     
    public LoggedUser(String username, String nickname, ActiveUserStore activeUserStore) {
        this.username = username;
        this.nickname = nickname;
        this.activeUserStore = activeUserStore;
    }
     
    public LoggedUser() {}
 
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<Object> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user)) {
            users.add(user);
        }
    }
 
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<Object> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user)) {
            users.remove(user);
        }
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}
 
    
}