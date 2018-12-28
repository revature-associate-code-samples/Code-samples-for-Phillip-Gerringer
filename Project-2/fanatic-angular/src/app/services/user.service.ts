import { Injectable } from '@angular/core';
import { NewUser } from 'src/app/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigAPI } from '../models/configAPI';
import { ReviewApiService } from 'src/app/services/review-api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    public reviewService: ReviewApiService
  ) { }

  public createUser(user) {
    return this.http.post<NewUser>(ConfigAPI.spring_url + 'user/new/', user);
  }

  findById(id: number) {
    return this.http.get<NewUser>(ConfigAPI.spring_url + 'user/' + id);
  }
}
