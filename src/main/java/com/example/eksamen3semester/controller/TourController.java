package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.PinTour;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.PinRepository;
import com.example.eksamen3semester.repository.PinTourRepository;
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
    PinTourRepository pinTourRepository;

    TourController(TourRepository tourRepository, PinRepository pinRepository, PinTourRepository pinTourRepository) {
        this.tourRepository = tourRepository;
        this.pinRepository = pinRepository;
        this.pinTourRepository = pinTourRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Tour>> fetchAll() {
        return ResponseEntity.ok(tourRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> findOne(@PathVariable ("id") Long id) {
        if (tourRepository.findById(id).isPresent()) {
            Tour tester = tourRepository.findById(id).get();
            List<PinTour> pinTours = pinTourRepository.findAll();
            List<Pin> pins = new ArrayList<>();

            pinTours.forEach(combo -> {
                if (combo.getTourId().equals(tester.getTourId())) {
                    if (pinRepository.findById(combo.getPinId()).isPresent()) {
                        pins.add(pinRepository.findById(combo.getPinId()).get());
                    }
                }
            });
            List<Tour.ShortPinList> shortPins = new ArrayList<>();
            pins.forEach(pin -> {
                Tour.ShortPinList shortPin = new Tour.ShortPinList(pin.getPinId(), pin.getDescription());
                shortPins.add(shortPin);
            });
            tester.setShortPinList(shortPins);
            pins.forEach(x -> System.out.println(x.getPinId()));
            return ResponseEntity.ok(tester);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
