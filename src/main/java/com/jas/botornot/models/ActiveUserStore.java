package com.jas.botornot.models;

import java.util.ArrayList;
import java.util.List;

public class ActiveUserStore {
	 
    public List<Object> users;
 
    public ActiveUserStore() {
        users = new ArrayList<Object>();
    }

	public List<Object> getUsers() {
		return users;
	}

	public void setUsers(List<Object> users) {
		this.users = users;
	}
 
    
}

