package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.repository.MediaLinkRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    MediaLinkRepository mediaLinkRepository;


    public MediaController(MediaLinkRepository  mediaLinkRepository) {
        this.mediaLinkRepository = mediaLinkRepository;
    }


    @GetMapping()
    public ResponseEntity<List<MediaLink>> fetchAll() {
        return ResponseEntity.ok(mediaLinkRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaLink> findOne(@PathVariable ("id") Long id) {
        if (mediaLinkRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(mediaLinkRepository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
