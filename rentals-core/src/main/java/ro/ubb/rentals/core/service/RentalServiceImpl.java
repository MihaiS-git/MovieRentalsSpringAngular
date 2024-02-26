package ro.ubb.rentals.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.rentals.core.model.Rental;
import ro.ubb.rentals.core.repository.RentalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements IRentalService {
    private static final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private IMovieService movieService;

    @Override
    public List<Rental> getAllRentals() {
        log.trace("getAllRentals --- method entered");

        List<Rental> rentals = rentalRepository.findAll();

        log.trace("getAllRentals: rentals={}", rentals);

        return rentals;
    }

    @Override
    public Rental getRentalById(Long id) {
        log.trace("getRentalById --- method entered");

        Rental result = rentalRepository.findById(id).orElse(new Rental());

        log.trace("getRentalById: rental={}", result);

        return result;
    }

    @Override
    public Rental rentAMovie(Rental rental) {
        log.trace("rentAMovie --- method entered");

        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusDays(1);
        float moviePrice = movieService.getMovieById(rental.getMovieId()).getRentalPrice();
        rental.setRentalDate(currentDate);
        rental.setDueDate(dueDate);
        rental.setRentalCharge(moviePrice);

        Rental result = rentalRepository.save(rental);

        log.trace("rentAMovie: result={}", result);

        return result;
    }

    @Transactional
    @Override
    public Rental updateRentalTransaction(Long rentalId, Rental rental) {
        log.trace("updateRentalTransaction --- method entered");

        Optional<Rental> result = rentalRepository.findById(rentalId);

        float moviePrice = movieService.getMovieById(rental.getMovieId()).getRentalPrice();

        result.ifPresent(o -> {
            o.setMovieId(rental.getMovieId());
            o.setClientId(rental.getClientId());
            o.setRentalCharge(moviePrice);
        });

        log.trace("updateRentalTransaction: rental={}", result.get());

        return result.orElse(null);
    }

    @Override
    public void deleteMovieRental(Long rentalId) {
        log.trace("deleteMovieRental --- method entered");

        rentalRepository.deleteById(rentalId);

        log.trace("deleteMovieRental: movie deleted successfully");
    }

    @Override
    public Iterable<Rental> findByClientId(Long clientId) {
        log.trace("findByClientId --- method entered");

        Iterable<Rental> result = rentalRepository.findByClientId(clientId);

        log.trace("findByClientId: result={}", result);

        return result;
    }

    @Override
    public Iterable<Rental> orderRentalsByRentalDateAsc() {
        log.trace("orderRentalsByRentalDateAsc --- method entered");

        Iterable<Rental> orderedRentals = rentalRepository.findAllByOrderByRentalDateAsc();

        log.trace("orderRentalsByRentalDateAsc: result={}", orderedRentals);

        return orderedRentals;
    }

    @Override
    public Iterable<Rental> orderRentalsByRentalDateDesc() {
        log.trace("orderRentalsByRentalDateDesc --- method entered");

        Iterable<Rental> orderedRentals = rentalRepository.findAllByOrderByRentalDateDesc();

        log.trace("orderRentalsByRentalDateDesc: result={}", orderedRentals);

        return orderedRentals;
    }
}
