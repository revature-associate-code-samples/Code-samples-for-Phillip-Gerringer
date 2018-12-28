package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.beans.User;
import com.ex.service.UserService;

@RestController
@RequestMapping("/users") //this annotation can be applied to both classes and methods
public class UserController {
	
	@Autowired
	private UserService service;
	
	//GET ALL
	@RequestMapping(method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAll(){
		return service.getAll();
	}
	
	//GET BY ID	
	@RequestMapping(value="/{id}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findById(@PathVariable int id) {
		User u = service.getById(id);
		
		if(u == null) {
			//return not found status
			return new ResponseEntity<User>(HttpStatus.I_AM_A_TEAPOT);
		}
		else {
			//return ok status
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	
	//CREATE
	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User u) {
		u = service.addUser(u);
		if(u == null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<User>(u, HttpStatus.CREATED);
		}
	}
	
	//UPDATE 
	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User u){
		User user = service.updateUser(id, u);
		if(user == null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
	}

}
