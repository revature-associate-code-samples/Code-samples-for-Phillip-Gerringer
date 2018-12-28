import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Actor } from 'src/app/models/actor';
import { Tabulator } from 'tabulator-tables/dist/js/tabulator.min.js';
import { ActorApiService } from 'src/app/services/actor-api.service';
import { OMDBAPI } from 'src/app/models/OMDBAPI';
import { MovieAPIService } from 'src/app/services/movie-api.service';

@Component({
  selector: 'app-actor-view',
  templateUrl: './actor-view.component.html',
  styleUrls: ['./actor-view.component.css']
})
export class ActorViewComponent implements OnInit {
  private id: string;
  public firstName: string;
  public lastName: string;
  public age: number;
  public actor: Actor;

  public actMovieArray = [];
  public total_pages: number;
  public current_page: number;
  public tempActMovie = [];

  constructor(
    public route: ActivatedRoute,
    public actorService: ActorApiService,
    public movieService: MovieAPIService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.actorService.getActor(this.id).subscribe(
      (actor) =>  {
        this.actor = actor;
        this.actor.profile_path = this.actorService.formatActorImage(this.actor.profile_path);
      });
    this.getMovies();
  }

  getMovies() {
    this.movieService.getMoviesByActor(this.id).subscribe(
      (movie) =>  {
                    this.tempActMovie.push(movie);
                    this.total_pages = this.tempActMovie[0].total_pages;
                    this.current_page = 1;
                    let nums;
                    if (this.tempActMovie[0].total_results < 6) {
                      nums = this.tempActMovie[0].total_results;
                    } else {
                      nums = 6;
                    }
                    for (let i = 0; i < nums; i++) {
                      this.actMovieArray.push({'title': this.tempActMovie[0].results[i].original_title,
                                            'Poster' : this.movieService.formatImage(this.tempActMovie[0].results[i].poster_path),
                                            'id' : this.tempActMovie[0].results[i].id  });
                    }
                  });
  }
}
