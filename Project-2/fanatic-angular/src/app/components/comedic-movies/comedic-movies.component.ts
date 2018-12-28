import { Component, OnInit } from '@angular/core';
import { MovieAPIService } from '../../services/movie-api.service';

@Component({
  selector: 'app-comedic-movies',
  templateUrl: './comedic-movies.component.html',
  styleUrls: ['./comedic-movies.component.css']
})
export class ComedicMoviesComponent implements OnInit {

  public comedyMovieArray = [];
  public total_pages: number;
  public current_page: number;
  public tempComedyMovie = [];

  constructor(
    public movieService: MovieAPIService
  ) { }

  ngOnInit() {
    this.getComedies();
  }

  getComedies() {
    this.movieService.getComedies().subscribe(
      (movie) =>  {
                    this.tempComedyMovie.push(movie);
                    this.total_pages = this.tempComedyMovie[0].total_pages;
                    this.current_page = 1;
                    for (let i = 0; i < 6; i++) {
                      this.comedyMovieArray.push({'title': this.tempComedyMovie[0].results[i].original_title,
                                            'Poster' : this.movieService.formatImage(this.tempComedyMovie[0].results[i].poster_path),
                                            'id' : this.tempComedyMovie[0].results[i].id  });
                    }
                  });
  }

}
