package com.example.eksamen3semester.config;

import com.example.eksamen3semester.model.MediaLink;
import com.example.eksamen3semester.model.MediaType;
import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.Tour;
import com.example.eksamen3semester.repository.MediaLinkRepository;
import com.example.eksamen3semester.repository.PinRepository;
import com.example.eksamen3semester.repository.TourRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSetup implements CommandLineRunner {
    PinRepository pinRepository;
    TourRepository tourRepository;
    MediaLinkRepository mediaLinkRepository;

    public DataSetup(PinRepository pinRepository, TourRepository tourRepository, MediaLinkRepository mediaLinkRepository){
        this.pinRepository = pinRepository;
        this.tourRepository = tourRepository;
        this.mediaLinkRepository = mediaLinkRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //HEJ MARC, VI SLETTER DET HER OG LAVER SQL STATEMENTS ISTEDET, FOR JPA ER SKRALD OG VI GIDER IKKE FINDE UD AF
        // HVORDAN DET FUNGERER :) HILSEN DEN BEDRE TUTOR

        List<MediaLink> listOfMediaLinks = new ArrayList<MediaLink>();
        List<Tour> listOfTour = new ArrayList<Tour>();
        List<Pin> listOfPins = new ArrayList<Pin>();

        //Pins
        Pin testPin = new Pin(3L,300,20,10,50,"lalala");
        listOfPins.add(testPin);

        //Tour
        Tour testTour = new Tour(1L,"lalalfdsf");
        listOfTour.add(testTour);


        //MediaLink
        MediaLink testML = new MediaLink(5L,"www.smileyface.dk", MediaType.VIDEO);
        listOfMediaLinks.add(testML);

        //testTour.setMediaLinks(listOfMediaLinks);

        pinRepository.save(testPin);
        tourRepository.save(testTour);
        mediaLinkRepository.save(testML);

    }


}
