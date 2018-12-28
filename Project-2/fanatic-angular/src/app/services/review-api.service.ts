import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Headers, RequestOptions } from '@angular/http';

import { Review } from '../models/review';
import { ConfigAPI } from 'src/app/models/configAPI';
import { ReviewCount } from 'src/app/models/reviewCount';
import { ReviewBean } from 'src/app/models/reviewBean';
import { Approval } from 'src/app/models/approval';
import { config } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewApiService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(public http: HttpClient) { }

  getMovieReviews(movie_id: number, source_id: number) {
    return this.http.get<Review[]>(ConfigAPI.spring_url + 'review/view/'
      + movie_id + '/' + source_id);
  }

  getReviewCount(movie_id: string) {
    return this.http.get<ReviewCount>(ConfigAPI.spring_url + 'view/rating/' + movie_id);
  }

  public getUserReviews(user_id: number) {
    return this.http.get<ReviewBean[]>(ConfigAPI.spring_url + '/review/all/' + user_id);
  }

  newReview(review: ReviewBean) {
   return this.http.post<ReviewBean>(ConfigAPI.spring_url + 'review/', review, this.httpOptions);
  }

  newApproval(approval: Approval) {
    return this.http.post<Approval>(ConfigAPI.spring_url + 'approval/', approval, this.httpOptions);
  }

  getApprovals(user_id: number) {
    return this.http.get<Approval[]>(ConfigAPI.spring_url + 'approval/' + user_id);
  }
}
