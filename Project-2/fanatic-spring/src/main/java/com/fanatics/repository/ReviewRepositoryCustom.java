package com.fanatics.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fanatics.beans.ReviewBean;
import com.fanatics.beans.RatingCountBean;

@Repository
public class ReviewRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;

	public List<ReviewBean> findAll(Integer source_id, Integer movie_id) {
		@SuppressWarnings("unchecked")
        List<ReviewBean> data = entityManager
        	.createNativeQuery("select r.review_id, u.user_name, TO_CHAR(u.join_date, 'MM/DD/YYYY') as join_date, "
        				+ "     TO_CHAR(r.review_date, 'MM/DD/YYYY') as review_date, " 
        				+ "		r.rating, r.review, a2.source_id as already_reviewed,  "
        				+ "		sum(a.thumbs_up) as UP, count(*)-sum(a.thumbs_up) as DOWN "
        				+ "from reviews r "
        				+ "join users u on r.user_id = u.user_id "
        				+ "left join approvals a on a.review_id = r.review_id "
        				+ "left join approvals a2 on a2.source_id = :source "
        				+ "		and a2.review_id = r.review_id "
        				+ "where r.review_expire_date > current_date and r.movie_id = :movie "
        				+ "group by r.review_id, u.user_name, u.join_date, r.review_date, "
        				+ "		r.rating, r.review, a2.source_id "
        				+ "order by r.review_date desc")
        	.setParameter("source",  source_id)
        	.setParameter("movie",  movie_id)
        	.getResultList();
        return data;
	}
	
	public RatingCountBean getRatingCount(Integer movie_id) {
		int iTotal = 0;
		int iRating = 0;
		
		BigDecimal total = (BigDecimal)entityManager
        	.createNativeQuery("select count(*) " + 
        						"from reviews where movie_id = :movie_id ")
        	.setParameter("movie_id",  movie_id)
        	.getSingleResult();
		if(total != null) {
			iTotal = total.intValue();
		}
		
		
		BigDecimal rating = (BigDecimal)entityManager
	        	.createNativeQuery("select round(avg(rating),0) " + 
	        						"from reviews where movie_id = :movie_id ")
	        	.setParameter("movie_id",  movie_id)
	        	.getSingleResult();
		if(rating != null) {
			iRating = rating.intValue();
		}
		
		return new RatingCountBean(iTotal, iRating);
	}
}
