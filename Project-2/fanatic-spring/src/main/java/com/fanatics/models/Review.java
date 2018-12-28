package com.fanatics.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity //registers class as entity in DB
@NamedQuery(name = "Review.getAllById", query = "select r "
											+ "from Review r "
											+ "where r.user_id = :userid")
@Table(name="REVIEWS")//allows further configuration of Table in DB
public class Review {
    @Id //necessary for Hibernate to identify objects
    @Column(name="REVIEW_ID")
    @SequenceGenerator(name="REV_SEQ_GEN", sequenceName="REV_SEQ")
    @GeneratedValue(generator="REV_SEQ_GEN", strategy=GenerationType.SEQUENCE)
    private int review_id;
    
    @Column(nullable=false, name="USER_ID")
    private int user_id;
    @Column(nullable=false, name="MOVIE_ID")
    private int movie_id;
    @Column(nullable=false, name="REVIEW")
    private String review;
    @Column(nullable=false, name="RATING")
    private int rating;
    @Column(nullable=false, name="REVIEW_DATE")
    private Timestamp review_date;
    @Column(nullable=false, name="REVIEW_EXPIRE_DATE")
    private Timestamp expire_date;
    
    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="REVIEW_ID",  insertable = false, updatable = false)
    private List<Approval> approvals;

    public Review() {
    }

    public Review(int review_id) {
        super();
        this.review_id = review_id;
    }

    public Review(int review_id, int user_id, int movie_id) {
        super();
        this.review_id = review_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getReview_date() {
        return review_date;
    }

    public void setReview_date(Timestamp review_date) {
        this.review_date = review_date;
    }

    public Timestamp getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Timestamp expire_date) {
        this.expire_date = expire_date;
    }

    @Override
    public String toString() {
        return "Review [review_id=" + review_id + ", user_id=" + user_id + ", movie_id=" + movie_id + ", review="
                + review + ", rating=" + rating +  ", review_date=" + review_date
                + ", expire_date=" + expire_date + "]";
    }
}