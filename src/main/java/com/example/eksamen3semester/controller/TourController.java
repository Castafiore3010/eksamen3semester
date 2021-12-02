package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.PinRepository;
import com.example.eksamen3semester.repository.TourRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    TourRepository tourRepository;
    PinRepository pinRepository;

    TourController(TourRepository tourRepository, PinRepository pinRepository) {
        this.tourRepository = tourRepository;
        this.pinRepository = pinRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Tour>> fetchAll() {
        return ResponseEntity.ok(tourRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> findOne(@PathVariable ("id") Long id) {
        if (tourRepository.findById(id).isPresent()) {
            Tour tester = tourRepository.findById(id).get();
            return ResponseEntity.ok(tester);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
