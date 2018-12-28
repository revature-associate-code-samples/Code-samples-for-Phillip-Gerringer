import { Component, OnInit } from '@angular/core';
import { MovieAPIService } from 'src/app/services/movie-api.service';
import { MovieAPI } from 'src/app/models/movieAPI';
import { ConfigAPI } from 'src/app/models/configAPI';
import { ColorService } from 'src/app/services/color.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-view',
  templateUrl: './search-view.component.html',
  styleUrls: ['./search-view.component.css']
})

export class SearchViewComponent implements OnInit {

  public movieArray = [];
  public actorArray = [];
  public query: string;
  public total_pages: number;
  public current_page: number;
  public tempMovie = [];
  public movieNotEmpty = false;
  public actorNotEmpty = false;
  public imglink;

  constructor(
    public route: ActivatedRoute,
    public movieService: MovieAPIService,
    public colorService: ColorService
  ) {}


   ngOnInit() {
    this.route.params.subscribe(param => {
      this.query = param['query'];
      this.getAllMovies();
    });
  }

  getAllMovies() {
    this.movieArray = [];
    this.actorArray = [];
    this.tempMovie = [];
    this.movieService.getMoviesByString(this.query).subscribe(
      (movie) =>  {
                    this.tempMovie.push(movie);
                    this.total_pages = this.tempMovie[0].total_pages;
                    this.current_page = 1;
                    for (let i = 0; i < this.tempMovie[0].results.length; i++) {
                      if (this.tempMovie[0].results[i].media_type === 'movie') {
                        if (this.tempMovie[0].results[i].poster_path == null) {
                          this.imglink = '/assets/noMovie.jpeg';
                        } else {
                          this.imglink = this.movieService.formatImage(this.tempMovie[0].results[i].poster_path);
                        }
                        this.movieArray.push({'title': this.tempMovie[0].results[i].original_title,
                                              'id': this.tempMovie[0].results[i].id,
                                              'Poster' : this.imglink  });
                      } else if (this.tempMovie[0].results[i].media_type === 'person') {
                        if (this.tempMovie[0].results[i].profile_path == null) {
                          this.imglink = '/assets/noActor.jpeg';
                        } else {
                          this.imglink = this.movieService.formatImage(this.tempMovie[0].results[i].profile_path);
                        }
                        this.actorArray.push({'name': this.tempMovie[0].results[i].name,
                        'id': this.tempMovie[0].results[i].id,
                        'Profile' : this.imglink });
                      }
                    }

                    if (this.actorArray.length >= 1) {
                      this.actorNotEmpty = true;
                    }
                    if (this.movieArray.length >= 1) {
                      this.movieNotEmpty = true;
                    }
                  });
  }
}
