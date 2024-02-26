import { Component, Input, OnInit } from '@angular/core';
import { Rental } from '../shared/rental.model';
import { RentalService } from '../shared/rental.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { of, switchMap } from 'rxjs';

@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.css']
})
export class RentalDetailComponent implements OnInit {
  @Input() rental!: Rental;

  constructor(
    private rentalService: RentalService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .pipe(
        switchMap((params: Params) => {
          const rentalId = params['id'];
          if (isNaN(+rentalId)) {
            return of(<Rental>{})
          } else {
            return this.rentalService.getRental(+rentalId);
          }
        })
      )
      .subscribe((rental) => {
        this.rental = rental ?? <Rental>{};
      });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.rentalService.update(this.rental).subscribe((_) => this.goBack());
  }

  delete(): void {
    this.rentalService.delete(this.rental.id).subscribe((_) => this.goBack());
  }
}
