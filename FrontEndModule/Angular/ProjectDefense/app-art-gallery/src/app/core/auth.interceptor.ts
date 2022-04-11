import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { IUser } from '../interfaces';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    console.log('auth interceptor');
    
    return next.handle(request).pipe(tap(response => {
        if (response instanceof HttpResponse) {
            if (response.url.endsWith("login") || response.url.endsWith("register")) {
              const newlyLoggedUser: IUser = response.body as IUser;
              this.authService.handleLogin(newlyLoggedUser);
            } else if (response.url.endsWith("logout")) {
              this.authService.handleLogout(undefined);
            } 
        }
    }));
  }
}
