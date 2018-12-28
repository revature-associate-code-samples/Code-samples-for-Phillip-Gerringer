import { Component, OnInit } from '@angular/core';
import { MovieAPIService } from 'src/app/services/movie-api.service';

@Component({
  selector: 'app-year-movies',
  templateUrl: './year-movies.component.html',
  styleUrls: ['./year-movies.component.css']
})
export class YearMoviesComponent implements OnInit {

  public yearMovieArray = [];
  public total_pages: number;
  public current_page: number;
  public tempYearMovie = [];

  constructor(
    public movieService: MovieAPIService
  ) { }

  ngOnInit() {
    this.getMoviesByYear();
  }

  getMoviesByYear () {
    this.movieService.getMoviesByYear().subscribe(
      (movie) =>  {
                  this.tempYearMovie.push(movie);
                  this.total_pages = this.tempYearMovie[0].total_pages;
                  this.current_page = 1;
                  for (let i = 0; i < 6; i++) {
                    this.yearMovieArray.push({'title': this.tempYearMovie[0].results[i].original_title,
                                          'Poster' : this.movieService.formatImage(this.tempYearMovie[0].results[i].poster_path),
                                          'id' : this.tempYearMovie[0].results[i].id });
                  }
                  });

  }

}
