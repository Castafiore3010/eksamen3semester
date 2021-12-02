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
        List<MediaLink> listOfMediaLinks = new ArrayList<MediaLink>();
        List<Tour> listOfTour = new ArrayList<Tour>();
        List<Pin> listOfPins = new ArrayList<Pin>();

        //Pins
        Pin testPin = new Pin(null,300,20,10,50,"lalala");
        listOfPins.add(testPin);

        //Tour
        Tour testTour = new Tour(null,"lalalfdsf");
        listOfTour.add(testTour);

        //MediaLink
        MediaLink testML = new MediaLink(null,"www.smileyface.dk", MediaType.VIDEO);
        listOfMediaLinks.add(testML);



        //merge :)
        //testPin.setTours(listOfTour);
        //testPin.setMediaLinks(listOfMediaLinks);

        testTour.setPins(listOfPins);
        testTour.setMediaLinks(listOfMediaLinks);

        testML.setTours(listOfTour);
        testML.setPins(listOfPins);

        //testTour.setMediaLinks(listOfMediaLinks);

        pinRepository.save(testPin);
        //tourRepository.save(testTour);
        //mediaLinkRepository.save(testML);

    }


}
