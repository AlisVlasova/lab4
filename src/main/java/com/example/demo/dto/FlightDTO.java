package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private String departureCity;

    private String arrivalCity;

    private double price;

    private int flightNumber;
}
