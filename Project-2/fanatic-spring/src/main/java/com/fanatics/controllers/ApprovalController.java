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

import com.fanatics.models.Approval;
import com.fanatics.models.Review;
import com.fanatics.repository.ApprovalRepository;
import com.fanatics.util.Log;

/**
 * @author Gerringer
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/approval")
public class ApprovalController {
	@Autowired 
	private ApprovalRepository repo;
	
	static Logger log = Log.getInstance(ApprovalController.class);
	
	/**
	 * 
	 */
	public ApprovalController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * returns results from a GET method
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/{id}",
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Approval> getAll(@PathVariable Integer id){
		return repo.findById(id);
	} 
	
	/**
	 * Add an approval
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(method=RequestMethod.POST, 
					consumes=MediaType.APPLICATION_JSON_VALUE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Approval> postThumbs(@RequestBody Approval approval) {
		approval = repo.save(approval);
			
		if(approval == null) {
			//return not found status
			return new ResponseEntity<Approval>(HttpStatus.I_AM_A_TEAPOT);
		}
		else {
			//return ok status
			return new ResponseEntity<Approval>(approval, HttpStatus.OK);
		}
	}
}
