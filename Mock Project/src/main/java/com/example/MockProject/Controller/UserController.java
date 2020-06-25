package com.example.MockProject.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.Exception.ApiRequestException;
import com.example.MockProject.Model.User;
import com.example.MockProject.Repository.UserRepo;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	 @Autowired
	    private UserRepo userRepo;
		
	    @PostMapping("/signup")
	    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
	    	System.out.println(userRepo.findByMail(user.getEmail()));
	    	if (userRepo.findByMail(user.getEmail()) == 0 ) {
	        	User result = userRepo.save(user);
	            return ResponseEntity.created(new URI("/api/users" + result.getId())).body(result);
	        }
	        throw new ApiRequestException("email id already exists");

	    }
	    
	    @GetMapping("/login")
	    @ResponseBody
	    public ResponseEntity<User> verifyUser(@Valid @RequestBody User user) throws URISyntaxException {
	    	
	
	    	
	    	if (userRepo.findByMail(user.getEmail()) == 1 ) {
	    		
	        	List<?> pwd = userRepo.findByPassword(user.getEmail());
	        	
	        	
	        	
	        		        		if (pwd.get(0).equals(user.getPassword())){
	        
        			User user_login = userRepo.findUserbyMail(user.getEmail());
	        			return ResponseEntity.ok().body(user_login);	  
        			
	        		}
	 
	        throw new ApiRequestException("Oops password not valid");
	        		        		
	        	}
	    	
	    	throw new ApiRequestException("Oops wrong email address");
	    	
	    	

	    }
	        //throw new ApiRequestException(ApiRequestException.VALID); 
}

