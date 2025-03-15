package com.example.cgi_praktika.API.dao.Service;

import com.example.cgi_praktika.API.dao.DataObjects.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

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
                               @Param("maxTime") LocalDateTime maxTime);
}
