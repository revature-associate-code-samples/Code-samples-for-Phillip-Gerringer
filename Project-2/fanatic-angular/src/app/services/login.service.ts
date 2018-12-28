import { Injectable, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ConfigAPI } from '../models/configAPI';

@Injectable({
 providedIn: 'root'
})
export class LoginService {
  private LOGGEDIN = 'loggedIn';
  private USERNAME = 'username';
  private ID = 'id';
  private TOKEN = 'token';
  private FULLNAME = 'fullname';

 httpOptions = {
   headers: new HttpHeaders({
     'Content-Type': 'application/json'
   })
 };
 usr: User;

 constructor(private http: HttpClient) {
   localStorage.setItem(this.LOGGEDIN, 'false');
 }

 public validateUser(username, password) {
   this.usr = new User(0, username, '', password, '', '', '');
   return this.http.post<User>(ConfigAPI.spring_url + 'user/login/', this.usr);
 }

 public persistLogin(user: User) {
   localStorage.setItem(this.ID, user.id.toString());
   localStorage.setItem(this.USERNAME, user.username);
   localStorage.setItem(this.TOKEN, user.token);
   localStorage.setItem(this.FULLNAME, user.firstname + ' ' + user.lastname);
   localStorage.setItem(this.LOGGEDIN, 'true');
 }

 public getUserName(): string {
   return localStorage.getItem(this.USERNAME);
 }

 public getFullName(): string {
   return localStorage.getItem(this.FULLNAME);
 }

 public getUserID(): number {
   return Number(localStorage.getItem(this.ID));
 }


 public isLoggedIn(): boolean {
   let loggedIn = localStorage.getItem(this.LOGGEDIN);

   // just in case loggedIn isn't in local storage
   if (loggedIn == null) {
     localStorage.setItem(this.LOGGEDIN, 'false');
     loggedIn = localStorage.getItem(this.LOGGEDIN);
   }

   return (loggedIn === 'true');
 }

 public logout() {
   localStorage.removeItem(this.ID);
   localStorage.removeItem(this.USERNAME);
   localStorage.removeItem(this.TOKEN);
   localStorage.removeItem(this.FULLNAME);
   localStorage.setItem(this.LOGGEDIN, 'false');
 }
}
