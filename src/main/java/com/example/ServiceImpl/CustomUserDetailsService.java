package com.example.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Reposatory.UserRepository;
import com.example.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
	private UserRepository repository;
    public User saveDetails(User userdetails) {
    	repository.save(userdetails);
    	System.out.println(userdetails.toString());
		return userdetails;
    	
    }
    public List<User>getallUsers(){
    	 List<User> list=repository.findAll();
    	return list;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
   
}
