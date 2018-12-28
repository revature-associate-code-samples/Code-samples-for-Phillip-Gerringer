package com.fanatics.beans;

import org.springframework.stereotype.Component;

@Component
public class FavoritesBean {
	
	private int favoriteId;
	private int userId;
	private int movieId;
	
	public FavoritesBean () {}
	
	
	public FavoritesBean(int favoriteId, int userId, int movieId) {
		super();
		this.favoriteId = favoriteId;
		this.userId = userId;
		this.movieId = movieId;
	}
	public int getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	@Override
	public String toString() {
		return "FavoritesBean [favoriteId=" + favoriteId + ", userId=" + userId + ", movieId=" + movieId + "]";
	}
	
	
	
}
