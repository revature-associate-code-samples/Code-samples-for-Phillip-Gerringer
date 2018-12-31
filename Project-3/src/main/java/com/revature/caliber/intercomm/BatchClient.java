package com.revature.caliber.intercomm;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.BatchEntity;

//@FeignClient(name="batch-service",  url="http://caliber-v2-1680103809.eu-west-2.elb.amazonaws.com/batch")
@FeignClient(name="batch-service",  url="${BATCH_SERVICE_URL}") 
public interface BatchClient {
	
	@GetMapping("/qc/batch/all")
	//@GetMapping({ "/qc/batch/all", "/vp/batch/all" })
	public List<BatchEntity> getAllBatches();

	
	@GetMapping("/all/batch/{id}")
	public BatchEntity getBatchById(@PathVariable("id") Integer id);
	
	
	@GetMapping("/valid_year")
	public List<Integer> batchYears();
	
//	/**
//	 * Accepts a HTTP Get Request. Mapped to ProjectURL/year/{path variable}
//	 * Returns a List<BatchEntity> which contains Batches in the given year.
//	 * The List is returned as a JSON Object.
//	 * @param year An Integer representing the year. Year is taken in as a path variable.
//	 * @return A List<BatchEntity> of batches in the given year. Returned as a JSON object
//	 */
//	@GetMapping({"/qc/batch/{year}", "/vp/batch/{year}"})
//	public List<BatchEntity> getBatchesByStartYear(@PathVariable("year") Integer year);
}
