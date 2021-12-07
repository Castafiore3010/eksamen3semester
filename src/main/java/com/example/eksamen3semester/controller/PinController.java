package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.PinTour;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.MediaLinkRepository;
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
@CrossOrigin(origins = "http://localhost:63342")
public class PinController {

    PinRepository pinRepository;
    TourRepository tourRepository;
    MediaLinkRepository mediaLinkRepository;
    PinTourRepository pinTourRepository;

    PinController(PinRepository pinRepository, TourRepository tourRepository, MediaLinkRepository mediaLinkRepository, PinTourRepository pinTourRepository) {
        this.pinRepository = pinRepository;
        this.tourRepository = tourRepository;
        this.mediaLinkRepository = mediaLinkRepository;
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

        Pin managedPin = new Pin(pin.getPinId(), pin.getLatitude(), pin.getLongitude(), pin.getDescription());


        // handle tours
        List<Long> matchingTourIds = new ArrayList<>();
        List<Tour> newToursToCreate = new ArrayList<>();
        pin.getTours().forEach(tour -> matchingTourIds.add(tour.getTourId())); // Add Ids from received JSON tours [{}]
        pin.getTours().forEach(tour -> {
            if (tour.getTourId() == null) {
                newToursToCreate.add(tour);
            }
        });
        List<Tour> newlyAddedTours = tourRepository.saveAll(newToursToCreate); // save all new entities in db
        List<Tour> matchingTours = tourRepository.findAllById(matchingTourIds); // find all matching in db
        matchingTours.addAll(newlyAddedTours); // add new to matching
        managedPin.setTours(matchingTours); // set tour list for pin

        // handle media
        List<Long> matchingMediaIds = new ArrayList<>();
        List<MediaLink> newMediaLinksToCreate = new ArrayList<>();
        pin.getMediaLinks().forEach(tour -> matchingMediaIds.add(tour.getMediaLinkId())); // Add Ids from received JSON mediaLinks [{}]
        pin.getMediaLinks().forEach(mediaLink -> {
            if (mediaLink.getMediaLinkId() == null) {
                newMediaLinksToCreate.add(mediaLink);
            }
        });
        List<MediaLink> newlyAddedMediaLinks = mediaLinkRepository.saveAll(newMediaLinksToCreate); // save all new entities
        List<MediaLink> matchingLinks = mediaLinkRepository.findAllById(matchingMediaIds); // find all matching
        matchingLinks.addAll(newlyAddedMediaLinks); // concat
        managedPin.setMediaLinks(matchingLinks); // set list for pin


        return ResponseEntity.status(201).body(pinRepository.save(managedPin));

    }


}
