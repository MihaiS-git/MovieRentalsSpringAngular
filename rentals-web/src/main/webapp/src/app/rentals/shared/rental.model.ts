export interface Rental {
  id: number;
  movieId: number;
  clientId: number;
  rentalCharge: number;
  rentalDate: Date;
  dueDate: Date;
}
