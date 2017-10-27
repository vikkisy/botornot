package com.jas.botornot.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jas.botornot.models.ChatMessage;
import com.jas.botornot.models.Role;
import com.jas.botornot.models.User;
import com.jas.botornot.repositories.RoleRepository;
import com.jas.botornot.repositories.UserRepository;


@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<User> findAll(){
    		return userRepository.findAll();
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void saveCurDate(User user) {
    		Date cur = new Date();
    		user.setUpdatedAt(cur);
    		userRepository.save(user);
    }
    public List<User> findAdmins() {
    		return userRepository.findByRoles_NameIs("ROLE_ADMIN");
    	}
    public List<User> findUsers() {
		return userRepository.findByRoles_NameIs("ROLE_USER");
	}
    public void deleteUser(Long id) {
    		userRepository.delete(id);
    }
    public void makeAdmin(Long id) {
    		User user = userRepository.findOne(id);
    		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
    		userRepository.save(user);
    }
    public void update(User user) {
    		userRepository.save(user);
    }

} 