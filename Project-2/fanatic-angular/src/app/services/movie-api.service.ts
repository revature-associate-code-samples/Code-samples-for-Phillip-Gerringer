import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { ConfigAPI } from 'src/app/models/configAPI';
import { MovieAPI } from 'src/app/models/movieAPI';
import { CreditsAPI } from 'src/app/models/creditsAPI';
import { OMDBAPI } from 'src/app/models/OMDBAPI';
import { Review } from '../models/review';
import { Favorite } from 'src/app/models/favorite';

@Injectable({
  providedIn: 'root'
})
export class MovieAPIService {
  constructor(private http: HttpClient) { }

  getMovie(id: string) {
    return this.http.get<MovieAPI>(ConfigAPI.base_url + 'movie/' + id + ConfigAPI.api_key);
  }

  getMoviesByString(query: string) {
    return this.http.get<MovieAPI>(ConfigAPI.base_url + 'search/multi' + ConfigAPI.api_key + ConfigAPI.query_params + query);
  }

  getMoviesByActor(id: string) {
    return this.http.get<MovieAPI[]>(ConfigAPI.base_url + 'discover/movie' + ConfigAPI.api_key + ConfigAPI.query_params
      + 'sort_by=popularity.desc&with_cast=' + id);
  }

  getPopularMovies() {
    return this.http.get<MovieAPI[]>(ConfigAPI.base_url + 'discover/movie' + ConfigAPI.api_key + '&sort_by=popularity.desc');
  }

  getMoviesByYear() {
    return this.http.get<MovieAPI[]>(ConfigAPI.base_url + 'discover/movie' + ConfigAPI.api_key + ConfigAPI.query_params
      + 'primary_release_year=2018&sort_by=vote_average.desc');
  }

  getDramas() {
    return this.http.get<MovieAPI[]>(ConfigAPI.base_url + 'discover/movie' + ConfigAPI.api_key + ConfigAPI.query_params
      + 'sort_by=popularity.desc&with_genres=18');
  }

  getComedies() {
    return this.http.get<MovieAPI[]>(ConfigAPI.base_url + 'discover/movie' + ConfigAPI.api_key + ConfigAPI.query_params
      + 'sort_by=popularity.desc&with_genres=35');
  }

  /**
   * return the list of favorites for the user
   */
  getFavorites(id: number) {
    return this.http.get<Favorite[]>(ConfigAPI.spring_url + 'favorite/' + id);
  }

  getFavoritesByUsername(username: string) {
    return this.http.get<Favorite[]>(ConfigAPI.spring_url + 'favorite/username/' + username);
  }

  /**
   * save the favorite to the user
   */
  addFavorite(fav: Favorite) {
    return this.http.post<Favorite>(ConfigAPI.spring_url + 'favorite/', fav);
  }

  getCredits(id: string) {
    return this.http.get<CreditsAPI[]>(ConfigAPI.base_url + 'movie/' + id + '/credits' + ConfigAPI.api_key);
  }

  getOMDB(imdb_id: string) {
    return this.http.get<OMDBAPI>(ConfigAPI.omdapi_base_url + '?i=' + imdb_id +
      '&plot=full&' + ConfigAPI.omdapi_key);
  }

  getMovieReviews(movie_id: number, source_id: number) {
    return this.http.get<Review[]>(ConfigAPI.spring_url + 'review/view/'
      + movie_id + '/' + source_id);
  }

  formatPosterImage(image: string): string {
    if (image == null) {
      return '/assets/noMovie.jpeg';
    }

    return this.formatImage(image);
  }

  formatImage(image: string): string {
    return ConfigAPI.image_url + image;
  }

  /**
   * Angular doesn't like url() because its "unsafe"  The URL needs to be scrubbed
   * as a SafeStyle
   */
  public getBackground(backdrop) {
    return { 'background-image': `url(${ConfigAPI.image_url}${backdrop})` };
  }
}
