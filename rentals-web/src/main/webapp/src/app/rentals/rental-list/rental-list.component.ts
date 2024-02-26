import { Component, OnInit } from '@angular/core';
import { Rental } from '../shared/rental.model';
import { RentalService } from '../shared/rental.service';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'rental-list',
  templateUrl: './rental-list.component.html',
  styleUrls: ['./rental-list.component.css']
})
export class RentalListComponent implements OnInit {
  errorMessage: string = "";
  rentals: Array<Rental> = [];
  selectedRental: Rental | undefined;
  searchedClientId: number = 0;

  constructor(private rentalService: RentalService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getRentals();
  }

  getRentals() {
    this.rentalService.getRentals()
      .subscribe({
        next: (rentals) => {
          this.rentals = rentals
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  onSelect(rental: Rental): void {
    this.selectedRental = rental;
  }

  gotoDetail(): void {
    this.router.navigate(['/rental/detail', this.selectedRental?.id]);
  }

  deleteRental(): void {
    if (this.selectedRental) {
      this.rentalService.delete(this.selectedRental.id).subscribe({
        next: () => {
          this.rentals = this.rentals.filter(
            (rental) => rental.id !== this.selectedRental?.id
          );
          this.selectedRental = undefined;
        },
        error: (err: any) => {
          console.error("Error deleting rental: ", err);
        },
        complete: () => { },
      });
    }
  }

  addNewRental(): void {
    this.router.navigate(['/rental/detail', 'new']);
  }

  getRentalsOrderedByDateAsc() {
    this.rentalService.getRentalsOrderedByDateAsc()
      .subscribe({
        next: (rentals) => {
          this.rentals = rentals
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  getRentalsOrderedByDateDesc() {
    this.rentalService.getRentalsOrderedByDateDesc()
      .subscribe({
        next: (rentals) => {
          this.rentals = rentals
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      })
  }

  findRentalsByClientId(searchedClientId: number) {
    this.rentalService.findRentalsByClientId(searchedClientId)
      .subscribe({
        next: (rentals) => {
          this.rentals = rentals
        },
        error: (err: any) => {
          this.errorMessage = err
        },
        complete: () => {
        }
      });
  }
}
