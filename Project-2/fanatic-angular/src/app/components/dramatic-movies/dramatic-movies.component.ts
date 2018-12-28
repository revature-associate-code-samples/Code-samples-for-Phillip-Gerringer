import { Component, OnInit } from '@angular/core';
import { MovieAPIService } from '../../services/movie-api.service';

@Component({
  selector: 'app-dramatic-movies',
  templateUrl: './dramatic-movies.component.html',
  styleUrls: ['./dramatic-movies.component.css']
})
export class DramaticMoviesComponent implements OnInit {

  public dramaMovieArray = [];
  public total_pages: number;
  public current_page: number;
  public tempDramaMovie = [];

  constructor(
    public movieService: MovieAPIService
  ) { }

  ngOnInit() {
    this.getDramas();
  }

  getDramas() {
    this.movieService.getDramas().subscribe(
      (movie) =>  {
                    this.tempDramaMovie.push(movie);
                    this.total_pages = this.tempDramaMovie[0].total_pages;
                    this.current_page = 1;
                    for (let i = 0; i < 6; i++) {
                      this.dramaMovieArray.push({'title': this.tempDramaMovie[0].results[i].original_title,
                                            'Poster' : this.movieService.formatImage(this.tempDramaMovie[0].results[i].poster_path),
                                            'id' : this.tempDramaMovie[0].results[i].id  });
                    }
                  });
  }

}
