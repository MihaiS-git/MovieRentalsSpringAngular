import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Client} from "./client.model";

import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";


@Injectable()
export class ClientService {
  private clientsUrl = "http://localhost:9095/api/clients";

  constructor(private httpClient: HttpClient) {
  }


  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  getClientsOrderedByFirstNameAsc(): Observable<Client[]> {
    const clientsOrderedByFirstNameAscUrl = `${this.clientsUrl}/order/firstName/asc`
    return this.httpClient
      .get<Array<Client>>(clientsOrderedByFirstNameAscUrl);
  }

  getClientsOrderedByFirstNameDesc(): Observable<Client[]> {
    const clientsOrderedByFirstNameDescUrl = `${this.clientsUrl}/order/firstName/desc`
    return this.httpClient
      .get<Array<Client>>(clientsOrderedByFirstNameDescUrl);
  }

  findClientsByFirstName(searchName: string): Observable<Client[]> {
    const searchByFirstNameUrl = `${this.clientsUrl}/search/firstName?firstName=${searchName}`
    return this.httpClient
      .get<Array<Client>>(searchByFirstNameUrl);
  }

  getClient(id: number): Observable<Client | undefined> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(client => client.id === id))
      );
  }

  update(client: Client): Observable<Client> {
      const url = client.id ? `${this.clientsUrl}/${client.id}` : this.clientsUrl;
      if (client.id) {
        return this.httpClient.put<Client>(url, client);
      } else {
        return this.httpClient.post<Client>(url, client);
      }
  }

  delete( clientId: number): Observable<void> {
    const url = `${this.clientsUrl}/${ clientId}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.httpClient.delete<void>(url, { headers }).pipe(
      catchError((error) => {
        console.error('Error deleteing client', error);
        return throwError(error);
      })
    )

  }

}
