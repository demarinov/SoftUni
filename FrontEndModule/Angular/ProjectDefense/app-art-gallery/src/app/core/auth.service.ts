import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, map, BehaviorSubject, catchError, EMPTY } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreateUserDto } from './user.service';
import { IUser } from '../interfaces';
import { Store } from '@ngrx/store';
import { IRootState, login, logout } from '../+store';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  currentUser$ = this.store.select(globalState => globalState.currentUser);

  isLoggedIn$ = this.currentUser$.pipe(map(user => !!user));

  constructor(private httpClient: HttpClient, private store: Store<IRootState>) { }

  handleLogin(newUser: IUser) {
    this.store.dispatch(login({user: newUser}));
  }

  handleLogout(newUser: IUser) {
    this.store.dispatch(logout());
  }

  login$(userData : {email: string, password: string}) : Observable<IUser> {

    return this.httpClient.
    post<IUser>(`${environment.apiUrl}/login`,userData, {withCredentials: true, observe: 'response'})
    .pipe(
      tap(response => console.log(response)),
      map(response => response.body),
      );
  }

  authenticate$() : Observable<IUser> {

    return this.httpClient.
    get<IUser>(`${environment.apiUrl}/users/profile`, {withCredentials: true})
    .pipe(
      tap(currentProfile => this.handleLogin(currentProfile)),
      catchError(err => {
        return EMPTY;
      }),
      );
  }

  logout$() : Observable<void> {

    console.log('logout called');
    return this.httpClient.
    post<void>(`${environment.apiUrl}/logout`, {}, {withCredentials: true});
  }

  register$(userData: CreateUserDto) : Observable<IUser> {

    return this.httpClient.post<IUser>(`${environment.apiUrl}/register`, userData,{withCredentials:true});
  }
}
