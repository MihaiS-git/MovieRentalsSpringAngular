import { Component, Input, OnInit } from '@angular/core';
import { MovieService } from '../shared/movie.service';
import { Movie } from '../shared/movie.model';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css'],
})
export class MovieDetailComponent implements OnInit {
  @Input() movie!: Movie;
  genres: string[] = [];
  ageRestrictions: string[] = [];
  available: string[] = [];

  constructor(
    private movieService: MovieService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .pipe(
        switchMap((params: Params) => {
          const movieId = params['id'];
          if (isNaN(+movieId)) {
            return of(<Movie>{})
          } else {
            return this.movieService.getMovie(+movieId);
          }

        })
      )
      .subscribe((movie) => {
        this.movie = movie ?? <Movie>{};
        this.genres = [
          'ACTION',
          'COMEDY',
          'DRAMA',
          'FANTASY',
          'HORROR',
          'MYSTERY',
          'ROMANCE',
          'THRILLER',
          'UNKNOWN',
          'WESTERN',
        ];
        this.ageRestrictions = ['GA', 'PG', 'PG13', 'R', 'NC17', 'UNKNOWN'];
        this.available = ['true', 'false'];
      });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.movieService.update(this.movie).subscribe((_) => this.goBack());
  }

  delete(): void {
    this.movieService.delete(this.movie.id).subscribe((_) => this.goBack());
  }
}
