import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IUser } from '../interfaces';

export interface CreateUserDto {
  username: string, email: string, password: string, tel?:string
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isLoggedIn:boolean = this.isUserLogged;

  currentUser!: IUser;
  
  constructor(private httpClient: HttpClient) { }

  logout() {
    // localStorage.setItem('isLogged', "true");
  }

  login$(userData : {email: string, password: string}) : Observable<IUser> {
    // localStorage.setItem('isLogged', "false");
    return this.httpClient.
    post<IUser>(`${environment.apiUrl}/login`,userData, {withCredentials: true, observe: 'response'})
    .pipe(
      tap(response => console.log(response)),
      map(response => response.body),
      tap(user => this.currentUser = user)

      );
  }

  get isUserLogged() : boolean {
    return !!this.currentUser;
  }

  register$(userData: CreateUserDto) : Observable<IUser> {

    return this.httpClient.post<IUser>(`${environment.apiUrl}/register`, userData,{withCredentials:true});
  }

  getProfile$() : Observable<IUser> {

    return this.httpClient.
    get<IUser>(`${environment.apiUrl}/users/profile`, {withCredentials: true}).pipe(tap(user => this.currentUser=user));
  }
}
