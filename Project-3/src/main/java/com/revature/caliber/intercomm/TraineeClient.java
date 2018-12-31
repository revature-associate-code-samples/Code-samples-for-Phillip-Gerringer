package com.revature.caliber.intercomm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.caliber.beans.Trainee;

//@FeignClient(name = "user-service", url = "localhost:9085/")
//@FeignClient(name= "user-service", url = "http://caliber-v2-1680103809.eu-west-2.elb.amazonaws.com/user")
@FeignClient(name = "user-service", url = "${USER_SERVICE_URL}")
public interface TraineeClient {

	@GetMapping(value = "/all/trainee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainee>> findAllByBatch(@RequestParam("batch") Integer batch);

	@PutMapping(value = "all/trainee/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Trainee> updateTrainee(@Valid @RequestBody Trainee trainee);

}
