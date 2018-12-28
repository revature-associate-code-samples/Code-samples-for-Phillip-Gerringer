import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieAPIService } from 'src/app/services/movie-api.service';
import { ReviewApiService } from 'src/app/services/review-api.service';
import { MovieAPI } from 'src/app/models/movieAPI';
import { OMDBAPI } from 'src/app/models/OMDBAPI';
import { Review } from 'src/app/models/review';
import { ReviewBean } from 'src/app/models/reviewBean';
import { LoginService } from 'src/app/services/login.service';
import { Approval } from 'src/app/models/approval';

@Component({
  selector: 'app-movie-review',
  templateUrl: './movie-review.component.html',
  styleUrls: ['./movie-review.component.css']
})
export class MovieReviewComponent implements OnInit {
  @ViewChild('cancelBtn') closeBtn: ElementRef;
  public id: string;
  public movie: MovieAPI;
  public ratings: OMDBAPI;
  public alreadyReviewed = false;

  // table vars
  public dataSource: Review[] = [];
  //  = [
  //   new Review(1, 'bob', '10-20-2000', 'Bobby', 2, 'or even clipped your toe nails', '10-20-2000', 2000, 50),
  //   new Review(2, 'chester', '10-20-2000', 'Bobby', 3, 'or played a video game', '10-14-2018', 100, 20),
  //   new Review(2, 'lester', '10-20-2000', 'Bobby', 1, 'You could have went bowling.', '10-14-2018', 100, 20),
  //   new Review(2, 'carmen', '10-20-2000', 'Bobby', 4, 'I want some popcorn.', '10-14-2018', 100, 20),
  //   new Review(2, 'bart', '10-20-2000', 'Bobby', 3, 'I cant believe it either.', '10-14-2018', 100, 20),
  //   new Review(2, 'smoking', '10-20-2000', 'Bobby', 1, 'I cant believe I watched the whole thing.', '10-14-2018', 100, 20)
  // ];

  public add_rating = 0;
  public add_review = '';

  constructor(
    public route: ActivatedRoute,
    public movieService: MovieAPIService,
    public reviewService: ReviewApiService,
    public loginService: LoginService
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');

    // get the movie data
    this.movieService.getMovie(this.id).subscribe(
      (movie) =>  {
                    this.movie = movie;
                    this.movie.poster_path = this.movieService.formatImage(this.movie.poster_path);
                    this.movieService.getOMDB(this.movie.imdb_id).subscribe(
                      (omdb) => {
                                  this.ratings = omdb;
                                  this.getMovieReviews();
                                }
                    );
                  });
  }

  time_convert(num) {
    const hours = Math.floor(num / 60);
    const minutes = num % 60;
    return hours + ' hr ' + minutes + ' min';
  }

  date_convert(date: string) {
    const parts = date.split('-');
    return parts[0];
  }

  submit() {
    // create the review to send to the DB
    let review: ReviewBean = new ReviewBean(
      0,
      this.loginService.getUserID(),
      this.movie.id,
      this.add_review,
      this.add_rating,
      0,
      new Date(),
      new Date()
    );

    // submit the update
    this.reviewService.newReview(review).subscribe(
      (r) =>  {
        review = r;

        this.getMovieReviews();
        this.alreadyReviewed = true;
        // clean up the local vars
        this.add_rating = 0;
        this.add_review = '';
      }
    );

    // close the modal
    this.closeBtn.nativeElement.click();
  }

  zero(int: number) {
    if (int == null) {
      return 0;
    }
    return int;
  }

  postUp(review_id: number) {
    this.newApproval(new Approval(0, review_id, 1, this.loginService.getUserID()));
  }

  postDown(review_id: number) {
    this.newApproval(new Approval(0, review_id, 0, this.loginService.getUserID()));
  }

  newApproval(approval: Approval) {
    this.reviewService.newApproval(approval).subscribe(
      (a) => { this.getMovieReviews(); }
    );
  }

  getMovieReviews() {
    // make sure the dataSource is clear
    this.dataSource = [];

    this.reviewService.getMovieReviews(this.movie.id, this.loginService.getUserID()).subscribe(
      (reviewList) => {
                    reviewList.forEach(element => {
                      const review: Review = new Review(element[0], element[1], element[2], element[3],
                                                        element[4], element[5], element[6],
                                                        this.zero(element[7]),
                                                        this.zero(element[8])); 
                      if (review.username === this.loginService.getUserName()) {
                        this.alreadyReviewed = true;
                      }
                      this.dataSource.push(review);
                                                  }
                                      );
      }
    );
  }
}
