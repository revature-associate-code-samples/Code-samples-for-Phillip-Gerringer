/**
 * 
 */
package com.fanatics.beans;

import org.springframework.stereotype.Component;

/**
 * @author PGerringer
 			"select r.review_id, u.user_name, u.join_date, r.review_date, "
			+ "		r.rating, r.review, a2.source_id as already_reviewed,  "
			+ "		sum(a.thumbs_up) as UP, count(*)-sum(a.thumbs_up) as DOWN"
			+ "from reviews r"
			+ "join users u on r.user_id = u.user_id "
			+ "left join approvals a on a.review_id = r.review_id"
			+ "left join approvals a2 on a2.source_id = ?1 "
			+ "		and a2.review_id = r.review_id "
			+ "where r.review_expire_date > current_date "
			+ "group by r.review_id, u.user_name, u.join_date, r.review_date, "
			+ "		r.rating, r.review, a2.source_id "
			+ "order by r.review_date desc"
 */
@Component
public class ReviewBean {
	private int review_id;
	private String user_name;
	private String join_date;
	private String review_date;
	private int rating;
	private String review;
	private int already_reviewed;
	private int up;
	private int down;
	
	public ReviewBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewBean(int review_id, String user_name, String join_date, String review_date, int rating, String review,
			int already_reviewed, int up, int down) {
		super();
		this.review_id = review_id;
		this.user_name = user_name;
		this.join_date = join_date;
		this.review_date = review_date;
		this.rating = rating;
		this.review = review;
		this.already_reviewed = already_reviewed;
		this.up = up;
		this.down = down;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getAlready_reviewed() {
		return already_reviewed;
	}

	public void setAlready_reviewed(int already_reviewed) {
		this.already_reviewed = already_reviewed;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	@Override
	public String toString() {
		return "ReviewBean [review_id=" + review_id + ", user_name=" + user_name + ", join_date=" + join_date
				+ ", review_date=" + review_date + ", rating=" + rating + ", review=" + review + ", already_reviewed="
				+ already_reviewed + ", up=" + up + ", down=" + down + "]";
	}
}
