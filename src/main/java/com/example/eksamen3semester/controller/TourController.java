package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.PinTour;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.MediaLinkRepository;
import com.example.eksamen3semester.repository.PinRepository;
import com.example.eksamen3semester.repository.PinTourRepository;
import com.example.eksamen3semester.repository.TourRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "http://localhost:63342")
public class TourController {

    TourRepository tourRepository;
    PinRepository pinRepository;
    MediaLinkRepository mediaLinkRepository;
    PinTourRepository pinTourRepository;

    TourController(TourRepository tourRepository, PinRepository pinRepository, MediaLinkRepository mediaLinkRepository, PinTourRepository pinTourRepository) {
        this.tourRepository = tourRepository;
        this.pinRepository = pinRepository;
        this.mediaLinkRepository = mediaLinkRepository;
        this.pinTourRepository = pinTourRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Tour>> fetchAll() {
        return ResponseEntity.ok(tourRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> findOne(@PathVariable("id") Long id) {
        if (tourRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(tourRepository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    public ResponseEntity<Tour> insertOne(@RequestBody Tour tour) throws NullPointerException{


        Tour managedTour = new Tour(tour.getTourId(), tour.getDescription());

        //handle pins

        if (tour.getPins() != null) {
            List<Long> matchingPinIds = new ArrayList<>();
            List<Pin> newPinsToCreate = new ArrayList<>();
            tour.getPins().forEach(pin -> matchingPinIds.add(pin.getPinId()));
            tour.getPins().forEach(pin -> {
                if (pin.getPinId() == null) {
                    newPinsToCreate.add(pin);
                }
            });
            List<Pin> newlyAddedPins = pinRepository.saveAll(newPinsToCreate);
            List<Pin> matchingPins = pinRepository.findAllById(matchingPinIds);
            matchingPins.addAll(newlyAddedPins);
            managedTour.setPins(matchingPins);
        }


        // handle media
        if (tour.getMediaLinks() != null) {
            List<Long> matchingMediaIds = new ArrayList<>();
            List<MediaLink> newMediaLinksToCreate = new ArrayList<>();
            tour.getMediaLinks().forEach(mediaLink -> matchingMediaIds.add(mediaLink.getMediaLinkId()));
            tour.getMediaLinks().forEach(mediaLink -> {
                if (mediaLink.getMediaLinkId() == null) {
                    newMediaLinksToCreate.add(mediaLink);
                }
            });
            List<MediaLink> newlyAddedMediaLinks = mediaLinkRepository.saveAll(newMediaLinksToCreate);
            List<MediaLink> matchingLinks = mediaLinkRepository.findAllById(matchingMediaIds);
            matchingLinks.addAll(newlyAddedMediaLinks);
            managedTour.setMediaLinks(matchingLinks);
        }


        return ResponseEntity.status(201).body(tourRepository.save(managedTour));

    }


}

