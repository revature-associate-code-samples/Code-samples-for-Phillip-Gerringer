package com.fanatics.controllers;

import java.util.List;

import org.apache.log4j.Logger;
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

import com.fanatics.models.Favorite;
import com.fanatics.models.User;
import com.fanatics.repository.FavoriteRepository;
import com.fanatics.repository.UserRepository;
import com.fanatics.util.Log;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

	static Logger log = Log.getInstance(FavoriteController.class);
	
	@Autowired
	private FavoriteRepository repo;
	
	@Autowired
	private UserRepository uRepo;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{id}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Favorite>> findById(@PathVariable int id) {
		List<Favorite> favorites = repo.findByUserId(id);
		log.debug(favorites);
		
		if(favorites == null) {
			//return not found status
			return new ResponseEntity<List<Favorite>>(HttpStatus.I_AM_A_TEAPOT);
		}
		else {
			//return ok status
			return new ResponseEntity<List<Favorite>>(favorites, HttpStatus.OK);
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite f) {
		f = repo.save(f);
		
		if(f == null) {
			return new ResponseEntity<Favorite>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<Favorite>(f, HttpStatus.CREATED);
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/username/{username}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Favorite>> findByUsername(@PathVariable String username) {
		User u = uRepo.findByUsernameLikeIgnoreCase(username);
		List<Favorite> favorites = repo.findByUserId(u.getId());
		log.debug(favorites);
		
		if(favorites == null) {
			//return not found status
			return new ResponseEntity<List<Favorite>>(HttpStatus.I_AM_A_TEAPOT);
		}
		else {
			//return ok status
			return new ResponseEntity<List<Favorite>>(favorites, HttpStatus.OK);
		}
	}	
}
