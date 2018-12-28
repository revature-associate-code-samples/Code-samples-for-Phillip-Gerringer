package com.fanatics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanatics.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> getAllById(@Param("userid") Integer id);
	
}
