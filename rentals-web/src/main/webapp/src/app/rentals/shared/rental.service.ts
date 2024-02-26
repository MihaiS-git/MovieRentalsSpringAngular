import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Rental} from "./rental.model";

import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";


@Injectable()
export class RentalService {
  private rentalsUrl = "http://localhost:9095/api/rentals";

  constructor(private httpClient: HttpClient) {
  }


  getRentals(): Observable<Rental[]> {
    return this.httpClient
      .get<Array<Rental>>(this.rentalsUrl);
  }

  getRental(id: number): Observable<Rental | undefined> {
    return this.getRentals()
      .pipe(
        map(rentals => rentals.find(rental => rental.id === id))
      );
  }

  update(rental: Rental): Observable<Rental> {
      const url = rental.id ? `${this.rentalsUrl}/${rental.id}` : this.rentalsUrl;
      if (rental.id) {
        return this.httpClient.put<Rental>(url, rental);
      } else {
        return this.httpClient.post<Rental>(url, rental);
      }
  }

  delete(rentalId: number): Observable<void> {
    const url = `${this.rentalsUrl}/${rentalId}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.httpClient.delete<void>(url, { headers }).pipe(
      catchError((error) => {
        console.error('Error deleteing rental', error);
        return throwError(error);
      })
    )
  }

  getRentalsOrderedByDateAsc(): Observable<Rental[]> {
    const rentalsOrderedByDateAsc = `${this.rentalsUrl}/order/rentalDate/asc`;
    return this.httpClient
      .get<Rental[]>(rentalsOrderedByDateAsc);
  }

  getRentalsOrderedByDateDesc(): Observable<Rental[]> {
    const rentalsOrderedByDateDesc = `${this.rentalsUrl}/order/rentalDate/desc`
    return this.httpClient
      .get<Rental[]>(rentalsOrderedByDateDesc);
  }

  findRentalsByClientId(searchedClientId: number): Observable<Rental[]> {
    const rentalsByClientIdUrl = `${this.rentalsUrl}/search/clientId?clientId=${searchedClientId}`;
    return this.httpClient
      .get<Rental[]>(rentalsByClientIdUrl);
  }

}
