package com.example.demo.service;

import com.example.demo.dto.FlightDTO;
import com.example.demo.model.Flight;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;

    private CityService cityService;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setCreatedAt(LocalDateTime.now());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setPrice(flightDTO.getPrice());
        flight.setArrivalCity(cityService.findCityByName(flightDTO.getArrivalCity()));
        flight.setDepartureCity(cityService.findCityByName(flightDTO.getDepartureCity()));
        flight.setDepartureTime(LocalDateTime.now().plusHours(1));
        flight.setArrivalTime(LocalDateTime.now().plusHours(5));
        return flightRepository.save(flight);
    }

    public List<Flight> findByNumber(int number) {
        return flightRepository.findByFlightNumber(number);
    }

    protected Flight findById(int id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.orElse(null);
    }
}
