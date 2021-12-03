package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.PinTour;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.PinRepository;
import com.example.eksamen3semester.repository.PinTourRepository;
import com.example.eksamen3semester.repository.TourRepository;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pins")
public class PinController {

    PinRepository pinRepository;
    TourRepository tourRepository;
    PinTourRepository pinTourRepository;

    PinController(PinRepository pinRepository, TourRepository tourRepository, PinTourRepository pinTourRepository) {
        this.pinRepository = pinRepository;
        this.tourRepository = tourRepository;
        this.pinTourRepository = pinTourRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Pin>> fetchAll() {
       return ResponseEntity.ok(pinRepository.findAll());


    }
    @GetMapping("/{id}")
    public ResponseEntity<Pin> findOne(@PathVariable("id") Long id) {
        if (pinRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(pinRepository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pin> insertOne(@RequestBody Pin pin) {
        List<Long> tourIdsForPins = new ArrayList<>();
        List<Tour> allTours = tourRepository.findAll();
        pin.getTours().forEach(tour -> tourIdsForPins.add(tour.getTourId()));
        List<Tour> attachList = new ArrayList<>();
        List<Pin> allPins = pinRepository.findAll();
        Long newestId = allPins.get(allPins.size() - 1).getPinId() + 1L;
        System.out.println("DEBUG: " + newestId);
        boolean savePin = false;
        int counter = 0;
        PinTour pt = new PinTour();
        for (Long id : tourIdsForPins) {
            if (!allTours.get(counter).getTourId().equals(id)) {
                attachList.add(allTours.get(counter));
            } else {
                savePin = true;
                pt.setPinId(newestId);
                pt.setTourId(allTours.get(counter).getTourId());
                System.out.println("HEJ DEBUGGER");
                System.out.println(pt.getPinId() + " " + pt.getTourId());

            }
            counter++;
        }
        // TAG HÅND OM INDSÆTTELSE AF FLERE PinTours til tour_pins
        pin.setTours(attachList);
        pinRepository.save(pin);
        if (savePin) {
            pinTourRepository.save(pt);
        }
        return ResponseEntity.status(201).body(pin);

    }


}
