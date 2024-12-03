package com.example.demo.controller;

import com.example.demo.model.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    // Статичний список рейсів (емуляція бази даних)
    private static List<Flight> flights = new ArrayList<>();

    // Статичний блок ініціалізації списку рейсів
    static {
        flights.add(new Flight(1L, "Київ", "Львів",
                LocalDateTime.of(2024, 6, 15, 10, 0),
                LocalDateTime.of(2024, 6, 15, 11, 30),
                1500.0, "AB123"));
        flights.add(new Flight(2L, "Харків", "Одеса",
                LocalDateTime.of(2024, 6, 16, 14, 0),
                LocalDateTime.of(2024, 6, 16, 15, 45),
                1200.0, "CD456"));
    }

    // GET-метод для отримання всіх рейсів
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flights);
    }

    // GET-метод для отримання рейсу за ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flights.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);

        return flight != null
                ? ResponseEntity.ok(flight)   // Статус 200
                : ResponseEntity.notFound().build();
    }

    // POST-метод для створення нового рейсу
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        // Генеруємо новий ID
        flight.setId(generateNewId());

        flights.add(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(flight);
    }

    // DELETE-метод для видалення рейсу за ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        boolean removed = flights.removeIf(flight -> flight.getId().equals(id));

        return removed
                ? ResponseEntity.noContent().build()   // Статус 204
                : ResponseEntity.notFound().build();
    }

    // Допоміжний метод для генерації нового унікального ID
    private Long generateNewId() {
        return flights.stream()
                .mapToLong(Flight::getId)
                .max()
                .orElse(0L) + 1;
    }
}
