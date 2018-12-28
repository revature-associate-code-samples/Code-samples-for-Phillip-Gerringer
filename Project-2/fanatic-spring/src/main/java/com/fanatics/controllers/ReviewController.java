/**
 * 
 */
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

import com.fanatics.beans.ReviewBean;
import com.fanatics.models.Review;
import com.fanatics.repository.ApprovalRepository;
import com.fanatics.repository.ReviewRepository;
import com.fanatics.services.ReviewService;
import com.fanatics.util.Log;
import com.fanatics.util.Tool;

/**
 * @author Gerringer
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService service;
	
	@Autowired
	private ReviewRepository repo;
	
	@Autowired 
	ApprovalRepository approvalRepo;
	
	static Logger log = Log.getInstance(ReviewController.class);
	
	/**
	 * returns results from a GET method
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Review> getAll(){
		return repo.findAll();
	} 
	
	/**
	 * GET BY review_id method
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/all/{id}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Review> getAllById(@PathVariable Integer id) {
		 return repo.getAllById(id);
	}
	
	/**
	 * GET BY review_id method
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{id}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Review> findById(@PathVariable int id) {
		Review review = service.getById(id);
			
		if(review == null) {
			//return not found status
			return new ResponseEntity<Review>(HttpStatus.I_AM_A_TEAPOT);
		}
		else {
			//return ok status
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}
	}
	
	/**
	 * GET BY review_id method
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/view/{movie}/{source}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ReviewBean> getView(@PathVariable int movie, @PathVariable int source) {
		 return service.getView(source, movie);
	}
	
	/**
	 * Creates a new Review
	 * @param review
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Review> createNewReview(@RequestBody Review review) {
		review.setExpire_date(Tool.getTime(Tool.tenYears()));
		review.setReview_date(Tool.getTime(Tool.getCurrentDate()));
		
		log.debug(review.toString());
		review = repo.save(review); 
//		review = service.newReview(review);
		if(review == null) {
			return new ResponseEntity<Review>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<Review>(review, HttpStatus.CREATED);
		}
	}
}
