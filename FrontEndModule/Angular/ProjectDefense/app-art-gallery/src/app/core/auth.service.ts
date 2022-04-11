import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, map, BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreateUserDto } from './user.service';
import { IUser } from '../interfaces';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _currentUser = new BehaviorSubject<IUser>(undefined);

  currentUser$ = this._currentUser.asObservable();

  isLoggedIn$ = this.currentUser$.pipe(map(user => !!user));

  constructor(private httpClient: HttpClient) { }

  handleLogin(newUser: IUser) {
    console.log(newUser+"new");
    this._currentUser.next(newUser);
  }

  handleLogout(newUser: IUser) {
    console.log('logout');
    this._currentUser.next(newUser);
  }

  login$(userData : {email: string, password: string}) : Observable<IUser> {
    // localStorage.setItem('isLogged', "false");
    return this.httpClient.
    post<IUser>(`${environment.apiUrl}/login`,userData, {withCredentials: true, observe: 'response'})
    .pipe(
      tap(response => console.log(response)),
      map(response => response.body),
      );
  }

  logout$() : Observable<void> {
    // localStorage.setItem('isLogged', "false");
    console.log('logout called');
    return this.httpClient.
    post<void>(`${environment.apiUrl}/logout`, {}, {withCredentials: true});
  }

  register$(userData: CreateUserDto) : Observable<IUser> {

    return this.httpClient.post<IUser>(`${environment.apiUrl}/register`, userData,{withCredentials:true});
  }
}
