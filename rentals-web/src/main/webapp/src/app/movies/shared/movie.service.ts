import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Movie} from "./movie.model";

import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";


@Injectable()
export class MovieService {
  private moviesUrl = "http://localhost:9095/api/movies";

  constructor(private httpClient: HttpClient) {
  }


  getMovies(): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl);
  }

  getMoviesOrderedByTitleAsc(): Observable<Movie[]> {
    const orderedByTitleAscUrl = `${this.moviesUrl}/order/title/asc`
    return this.httpClient
      .get<Array<Movie>>(orderedByTitleAscUrl);
  }

  getMoviesOrderedByTitleDesc(): Observable<Movie[]> {
    const orderedUrlDesc = `${this.moviesUrl}/order/title/desc`
    return this.httpClient
      .get<Array<Movie>>(orderedUrlDesc);
  }

  findMoviesByTitle(title: string): Observable<Movie[]> {
    const searchByTitleUrl = `${this.moviesUrl}/search/title?title=${title}`
    return this.httpClient
      .get<Array<Movie>>(searchByTitleUrl);
  }

  getMovie(id: number): Observable<Movie | undefined> {
    return this.getMovies()
      .pipe(
        map(movies => movies.find(movie => movie.id === id))
      );
  }

  update(movie: Movie): Observable<Movie> {
      const url = movie.id ? `${this.moviesUrl}/${movie.id}` : this.moviesUrl;
      if (movie.id) {
        return this.httpClient.put<Movie>(url, movie);
      } else {
        return this.httpClient.post<Movie>(url, movie);
      }
  }

  delete(movieId: number): Observable<void> {
    const url = `${this.moviesUrl}/${movieId}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.httpClient.delete<void>(url, { headers }).pipe(
      catchError((error) => {
        console.error('Error deleteing movie', error);
        return throwError(error);
      })
    )
  }
}
