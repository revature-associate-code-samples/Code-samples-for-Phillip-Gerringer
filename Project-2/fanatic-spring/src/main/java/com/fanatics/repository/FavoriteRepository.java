package com.fanatics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fanatics.models.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer>{

	public List<Favorite> findByUserId(int id);

	
}
