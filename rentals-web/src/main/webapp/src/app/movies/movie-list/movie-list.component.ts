import { Component, OnInit } from '@angular/core';
import { Movie } from '../shared/movie.model';
import { MovieService } from '../shared/movie.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent implements OnInit {
  errorMessage: string = '';
  movies: Array<Movie> = [];
  selectedMovie: Movie | undefined;
  searchTitle: string = '';

  constructor(
    private movieService: MovieService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getMovies();
  }

  getMovies() {
    this.movieService.getMovies().subscribe({
      next: (movies) => {
        this.movies = movies;
      },
      error: (err: any) => {
        this.errorMessage = err;
      },
      complete: () => {},
    });
  }

  onSelect(movie: Movie): void {
    this.selectedMovie = movie;
  }

  gotoDetail(): void {
    this.router.navigate(['/movie/detail', this.selectedMovie?.id]);
  }

  deleteMovie(): void {
    if (this.selectedMovie) {
      this.movieService.delete(this.selectedMovie.id).subscribe({
        next: () => {
          this.movies = this.movies.filter(
            (movie) => movie.id !== this.selectedMovie?.id
          );
          this.selectedMovie = undefined;
        },
        error: (err: any) => {
          console.error('Error deleting movie: ', err);
        },
        complete: () => {},
      });
    }
  }

  addNewMovie(): void {
    this.router.navigate(['/movie/detail', 'new']);
  }

  getMoviesOrderedByTitleAsc() {
    this.movieService.getMoviesOrderedByTitleAsc().subscribe({
      next: (movies) => {
        this.movies = movies;
      },
      error: (err: any) => {
        this.errorMessage = err;
      },
      complete: () => {},
    });
  }

  getMoviesOrderedByTitleDesc() {
    this.movieService.getMoviesOrderedByTitleDesc().subscribe({
      next: (movies) => {
        this.movies = movies;
      },
      error: (err: any) => {
        this.errorMessage = err;
      },
      complete: () => {},
    });
  }

  findMoviesByTitle(title: string) {
    this.movieService.findMoviesByTitle(title).subscribe({
      next: (movies) => {
        this.movies = movies;
      },
      error: (err: any) => {
        this.errorMessage = err;
      },
      complete: () => {},
    });
  }
}
