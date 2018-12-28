import { Injectable } from '@angular/core';
import { ConfigAPI } from 'src/app/models/configAPI';
import { Actor } from '../models/actor';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ActorApiService {
  constructor(private http: HttpClient) { }

  getActor(id: string) {
    // console.log(this.http.get<Actor>(ConfigAPI.base_url + 'person/' + id + ConfigAPI.api_key));
    return this.http.get<Actor>(ConfigAPI.base_url + 'person/' + id + ConfigAPI.api_key);
  }

  formatActorImage(image: string): string {
    if (image == null) {
      return '/assets/noActor.jpeg';
    }

    return this.formatImage(image);
  }

  formatImage(image: string): string {
    return ConfigAPI.image_url + image;
  }
}


