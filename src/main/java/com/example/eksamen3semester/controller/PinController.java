package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.repository.PinRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping("/{id}")
    public ResponseEntity<Pin> insertOne(@RequestBody Pin pin) {
        return ResponseEntity.status(201).body(pinRepository.save(pin));

    }


}
