import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IArt } from '../interfaces/art';
import {environment} from '../../environments/environment';

const apiUrl: string = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ArtService {

  constructor(private http: HttpClient) { }

  
  loadArtList(): Observable<IArt[]> {
    return this.http.get<IArt[]> (`${apiUrl}/artworks`);
  }

  loadArtById(artId : string): Observable<IArt> {
    return this.http.get<IArt> (`${apiUrl}/artworks/${artId}`);
  }

  loadArtsByUserId(userId : string): Observable<IArt[]> {
    return this.http.get<IArt[]> (`${apiUrl}/user/artworks/${userId}`, {withCredentials: true});
  }

  addArt$(body: {name:string, imageUrl: string, price: string}) : Observable<IArt> {
    return this.http.post<IArt> (`${apiUrl}/artworks`,body, {withCredentials: true});
  }
}
