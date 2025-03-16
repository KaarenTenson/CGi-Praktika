package com.example.cgi_praktika.API.dao.Repositry;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import com.example.cgi_praktika.API.dao.DataObjects.Seat;
import com.example.cgi_praktika.API.dao.DataObjects.Window;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDestination(String destination);

    @Query("SELECT DISTINCT f.destination FROM Flight f")
    List<String> findAllDestinations();

    @Query("SELECT f FROM Flight f WHERE " +
            "(:departure IS NULL OR f.departure = :departure) AND " +
            "(:destination IS NULL OR f.destination = :destination) AND " +
            "(:minPrice IS NULL OR f.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR f.price <= :maxPrice) AND " +
            "(:minTime IS NULL OR f.departureTime >= :minTime) AND " +
            "(:maxTime IS NULL OR f.departureTime <= :maxTime)")
    List<Flight> filterFlights(@Param("departure") String departure,
                               @Param("destination") String destination,
                               @Param("minPrice") Integer minPrice,
                               @Param("maxPrice") Integer maxPrice,
                               @Param("minTime") LocalDateTime minTime,
                               @Param("maxTime") LocalDateTime maxTime,
                               Sort sort);
    @Query("SELECT f FROM Flight f LEFT JOIN FETCH f.seats WHERE f.id = :id")
    Optional<Flight> findByIdWithSeats(@Param("id") Long id);
    @Query("SELECT s FROM Seat s WHERE s.flight.id = :flightId")
    List<Seat> findSeatsByFlightId(@Param("flightId") Long flightId);
    @Query("SELECT w FROM Window w WHERE w.flight.id = :flightId")
    List<Window> findWindowsByFlightId(@Param("flightId") Long flightId);
    @Query("SELECT DISTINCT f.destination FROM Flight f WHERE LOWER(f.destination) LIKE LOWER(CONCAT('%', :string, '%'))")
    List<String> findAllDestinationsThatContainString(@Param("string") String string);
    @Query("SELECT DISTINCT f.departure FROM Flight f WHERE LOWER(f.departure) LIKE LOWER(CONCAT('%', :string, '%'))")
    List<String> findAllDeparturesThatContainString(@Param("string") String string);
}
