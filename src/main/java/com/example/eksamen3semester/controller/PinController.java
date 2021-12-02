package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.PinRepository;
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

    PinController(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
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
        pinRepository.save(pin);
        return ResponseEntity.status(201).body(pin);

    }


}
