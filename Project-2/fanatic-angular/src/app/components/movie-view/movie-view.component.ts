import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieAPIService } from 'src/app/services/movie-api.service';
import { MovieAPI } from 'src/app/models/movieAPI';
import { ConfigAPI } from 'src/app/models/configAPI';
import { CastAPI } from 'src/app/models/castAPI';
import { CrewAPI } from 'src/app/models/crewAPI';
import { OMDBAPI } from 'src/app/models/OMDBAPI';
import { ColorService } from 'src/app/services/color.service';
import { LoginService } from 'src/app/services/login.service';
import { ReviewApiService } from 'src/app/services/review-api.service';
import { CompileShallowModuleMetadata } from '@angular/compiler';
import { Favorite } from 'src/app/models/favorite';

@Component({
  selector: 'app-movie-view',
  templateUrl: './movie-view.component.html',
  styleUrls: ['./movie-view.component.css']
})
export class MovieViewComponent implements OnInit {
  private id: string;
  public movie: MovieAPI;
  public ratings: OMDBAPI;
  public favorite: Favorite;
  public isFavorite = false;
  public credits = [];
  public cast = [];
  public castList = '';
  public directorList = [];
  public producerList = [];
  public listOfCast = [];
  public num_of_reviews = -1;

  constructor(
    public route: ActivatedRoute,
    public movieService: MovieAPIService,
    public reviewService: ReviewApiService,
    public colorService: ColorService,
    public router: Router,
    public loginService: LoginService
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');

    // get the movie data
    this.movieService.getMovie(this.id).subscribe(
      (movie) => {
        this.movie = movie;
        this.movie.poster_path = this.movieService.formatPosterImage(this.movie.poster_path);
        this.movieService.getOMDB(this.movie.imdb_id).subscribe(
          (omdb) => {
              this.ratings = omdb;
              this.getRottenTomatoes();
              // get the review count
              this.reviewService.getReviewCount(this.id).subscribe(
                (reviewCount) => {
                  this.num_of_reviews = reviewCount.total;
                  this.ratings.MovieFanatic = reviewCount.rating;
              });
          },
          err => {
            this.ratings.Metascore = 'N/A';
          });
      });

    // get the credits of the movie
    this.movieService.getCredits(this.id).subscribe(
      (creditList) => {
        this.credits.push(creditList);
        this.cast = this.sortCast(this.credits[0].cast);
        this.buildDirectorProducerLists(this.credits[0].crew);
      });

    // get the favorites
    this.movieService.getFavorites(this.loginService.getUserID()).subscribe(
      (favorite) => {
        for (let i = 0; i < favorite.length; i++) {
          if (favorite[i].movie_id === Number(this.id)) {
            this.isFavorite = true;
          }
        }
      }
    );
  }

  /**
   * forwards on to the review screen.
   */
  goMovieReview() {
    this.router.navigateByUrl('/movie/review/' + this.movie.id);
  }

  /**
   * Angular doesn't like url() because its "unsafe"  The URL needs to be scrubbed
   * as a SafeStyle
   */
  public getBackground() {
    return { 'background-image': `url(${ConfigAPI.image_url}${this.movie.backdrop_path})` };
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

  /**
   * grabs the Rotten Tomatoes score from the ratings array
   */
  private getRottenTomatoes() {
    if (this.ratings.Ratings) {
      this.ratings.Ratings.forEach(element => {
        if (element.Source === 'Rotten Tomatoes') {
          this.ratings.RottenTomatoes = element.Value;
        }
      });
    }
  }

  /**
   * sort the cast list
   */
  public sortCast(data: CastAPI[]): CastAPI[] {
    // sort the cast list based on the credit order
    return data.sort((c1, c2) => {
      if (c1.order > c2.order) {
        return 1;
      }
      if (c1.order < c2.order) {
        return -1;
      }
      return 0;
    });
  }

  public routeActor(id: number) {
    this.router.navigateByUrl('/actor/' + id);
  }

  /**
   * populate the Director and Producer lists
   */
  private buildDirectorProducerLists(data: CrewAPI[]) {
    data.forEach(element => {
      if (element.job === 'Director') {
        this.directorList.push(element);
      } else if (element.job === 'Producer') {
        this.producerList.push(element);
      }
    });
  }

  public addFavorite() {
    this.favorite = new Favorite(0, this.loginService.getUserID(), Number(this.id));
    this.movieService.addFavorite(this.favorite).subscribe (
      (favorite) => { this.isFavorite = true; }
    );
  }

  getFavorites() {
    this.movieService.getFavorites(this.loginService.getUserID()).subscribe(
      (favorite) => {

        for (let i = 0; i < favorite.length; i++) {
          if (favorite[i].movie_id === Number(this.id)) {
            this.isFavorite = true;
          }
        }
      }
    );
  }
}
