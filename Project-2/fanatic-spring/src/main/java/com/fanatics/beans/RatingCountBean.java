/**
 * 
 */
package com.fanatics.beans;

import org.springframework.stereotype.Component;

/**
 * @author PGerringer
 * 
 * select count(*) as TOTAL, round(avg(rating),0) as rating 
 * from reviews 
 * where movie_id = 399360
 */
@Component
public class RatingCountBean {
	private int total = 0;
	private int rating = 0;

	public RatingCountBean() {
		super();
	}

	public RatingCountBean(int total, int rating) {
		super();
		this.total = total;
		this.rating = rating;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewCountBean [total=" + total + ", rating=" + rating + "]";
	}
}
