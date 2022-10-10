import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Movie } from '../shared/models/movie';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  constructor(private httpClient: HttpClient) {
  }

  store(movie: Movie): Observable<Movie> {
    return this.httpClient.post<Movie>(environment.baseUrlMovies, movie);
  }
}
