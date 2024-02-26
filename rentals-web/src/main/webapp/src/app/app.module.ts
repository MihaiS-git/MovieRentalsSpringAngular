import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import { MoviesComponent } from './movies/movies.component';
import { MovieListComponent } from './movies/movie-list/movie-list.component';
import { MovieDetailComponent } from './movies/movie-detail/movie-detail.component';
import { MovieService } from './movies/shared/movie.service';
import { ClientsComponent } from './clients/clients.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import { ClientService } from './clients/shared/client.service';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { RentalsComponent } from './rentals/rentals.component';
import { RentalListComponent } from './rentals/rental-list/rental-list.component';
import { RentalDetailComponent } from './rentals/rental-detail/rental-detail.component';
import { RentalService } from './rentals/shared/rental.service';


@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    MovieListComponent,
    MovieDetailComponent,
    ClientsComponent,
    ClientDetailComponent,
    ClientListComponent,
    RentalsComponent,
    RentalListComponent,
    RentalDetailComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [MovieService, ClientService, RentalService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
