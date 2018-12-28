package com.ex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.beans.User;

@Service("userService")
public class UserService {

	//where we would autowire our daos but this is a dummy service

	static ArrayList<User> users = new ArrayList<User>();
	static int seq = 3;

	static {
		users.add(new User(1, "gab12", "123", "Genesis is awesome. wow"));
		users.add(new User(2, "test", "user", "testing"));
	}

	public List<User> getAll(){
		return users;
	}

	public User getById(int id) {
		return users.stream().filter(t -> t.getId()==id).findFirst()
				.orElse(null);
	}

	public User findByUsername(String username) {
		return users.stream().filter(t -> t.getUsername().
				equalsIgnoreCase(username)).findFirst().orElse(null);
	}

	public User addUser(User u) {
		User test = findByUsername(u.getUsername());
		if(test==null) {
			u.setId(seq++);
			users.add(u);
			return u;
		}
		else {
			return null;
		}
	}
	
	public User updateUser(int id, User u) {
		User old = getById(id);
		if(old == null) {
			return addUser(u);
		}
		else {
			int index = 0;
			//weird work around to change in-memory store of users
			for(User s : users) {
				if(s.getId() == id) {
					s.setBio(u.getBio());
					s.setUsername(u.getUsername()); //make sure username doesnt exist
					s.setPassword(u.getPassword());
					return s;
				}
			}
		}
		return null;
	}

}
