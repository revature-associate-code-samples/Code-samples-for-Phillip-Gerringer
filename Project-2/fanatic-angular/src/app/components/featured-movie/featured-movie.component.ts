import { Component, OnInit } from '@angular/core';
import { MovieAPIService } from '../../services/movie-api.service';
import { MovieAPI } from 'src/app/models/movieAPI';
import { ConfigAPI } from 'src/app/models/configAPI';

@Component({
  selector: 'app-featured-movie',
  templateUrl: './featured-movie.component.html',
  styleUrls: ['./featured-movie.component.css']
})
export class FeaturedMovieComponent implements OnInit {

  public featMovieId;
  public featMovie;
  public total_pages: number;
  public current_page: number;
  public tempFeatMovie = [];

  constructor(
    public movieService: MovieAPIService
  ) { }

  ngOnInit() {
    this.getFeaturedMovie();
  }

  getFeaturedMovie() {
    this.movieService.getPopularMovies().subscribe(
      (movie) =>  {
                    this.tempFeatMovie.push(movie);
                    this.total_pages = this.tempFeatMovie[0].total_pages;
                    this.current_page = 1;
                    this.featMovie = ({'title': this.tempFeatMovie[0].results[0].original_title,
                                            'Poster' : this.movieService.formatImage(this.tempFeatMovie[0].results[0].poster_path),
                                            'id' : this.tempFeatMovie[0].results[0].id,
                                            'tagline' : this.tempFeatMovie[0].results[0].tagline,
                                            'background' : this.tempFeatMovie[0].results[0].backdrop_path,
                                            'overview' : this.tempFeatMovie[0].results[0].overview  });
                  });
  }

  public getBackground() {
    return {'background-image': `url(${ConfigAPI.image_url}${this.featMovie.background})`};
  }
}
