import { Component, Input, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import { Client } from '../shared/client.model';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit {
  @Input() client!: Client;
  subscribe: string[] = [];

  constructor(
    private clientService: ClientService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .pipe(
        switchMap((params: Params) => {
          const clientId = params['id'];
          if (isNaN(+clientId)) {
            return of(<Client>{})
          } else {
            return this.clientService.getClient(+clientId);
          }

        })
      )
      .subscribe((client) => {
        this.client = client ?? <Client>{};
        this.subscribe = ['true', 'false'];
      });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.clientService.update(this.client).subscribe((_) => this.goBack());
  }

  delete(): void {
    this.clientService.delete(this.client.id).subscribe((_) => this.goBack());
  }

}
