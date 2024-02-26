import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../shared/client.model';
import { ClientService } from '../shared/client.service';

@Component({
  moduleId: module.id,
  selector: 'client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  errorMessage: string = "";
  clients: Array<Client> = [];
  selectedClient: Client | undefined;
  searchName: string = '';

  constructor(private clientService: ClientService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients()
      .subscribe({
        next: (clients) => {
          this.clients = clients
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  gotoDetail(): void {
    this.router.navigate(['/client/detail', this.selectedClient?.id]);
  }

  deleteClient(): void {
    if (this.selectedClient) {
      this.clientService.delete(this.selectedClient.id).subscribe({
        next: () => {
          this.clients = this.clients.filter(
            (client) => client.id !== this.selectedClient?.id
          );
          this.selectedClient = undefined;
        },
        error: (err: any) => {
          console.error("Error deleting client: ", err);
        },
        complete: () => { },
      });
    }
  }

  addNewClient(): void {
    this.router.navigate(['/client/detail', 'new']);
  }

  getClientsOrderedByFirstNameAsc() {
    this.clientService.getClientsOrderedByFirstNameAsc()
      .subscribe({
        next: (clients) => {
          this.clients = clients
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  getClientsOrderedByFirstNameDesc() {
    this.clientService.getClientsOrderedByFirstNameDesc()
      .subscribe({
        next: (clients) => {
          this.clients = clients
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  findClientsByFirstName(searchName: string) {
    this.clientService.findClientsByFirstName(searchName).subscribe({
      next: (clients) => {
        this.clients = clients;
      },
      error: (err: any) => {
        this.errorMessage = err;
      },
      complete: () => {},
    });
  }
}
