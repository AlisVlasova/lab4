package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    protected City findCityByName(String name) {
        return cityRepository.findByDisplayName(name);
    }

}
