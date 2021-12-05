package com.example.eksamen3semester.config;

import com.example.eksamen3semester.model.*;
import com.example.eksamen3semester.repository.BridgeRepository;
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
    BridgeRepository bridgeRepository;

    public DataSetup(PinRepository pinRepository, TourRepository tourRepository, MediaLinkRepository mediaLinkRepository
    , BridgeRepository bridgeRepository){
        this.pinRepository = pinRepository;
        this.tourRepository = tourRepository;
        this.mediaLinkRepository = mediaLinkRepository;
        this.bridgeRepository = bridgeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<MediaLink> listOfMediaLinks = new ArrayList<MediaLink>();
        List<Tour> listOfTour = new ArrayList<Tour>();
        List<Pin> listOfPins = new ArrayList<Pin>();

        BridgeStatus bridge = new BridgeStatus(1L, "open");


        //Pins
        Pin testPin = new Pin(null,300,20,10,50,"lalala");
        listOfPins.add(testPin);

        //Tour
        Tour testTour = new Tour(null,"lalalfdsf");
        listOfTour.add(testTour);

        Tour soloTour = new Tour(null, "SMiletur");

        //MediaLink
        MediaLink testML = new MediaLink(null,"www.smileyface.dk", MediaType.VIDEO);
        listOfMediaLinks.add(testML);



        //merge :)
        testPin.setTours(listOfTour);
        testPin.setMediaLinks(listOfMediaLinks);

        testTour.setPins(listOfPins);
        testTour.setMediaLinks(listOfMediaLinks);

        testML.setTours(listOfTour);
        testML.setPins(listOfPins);

        //testTour.setMediaLinks(listOfMediaLinks);

        pinRepository.save(testPin);
        //tourRepository.save(soloTour);
        //bridgeRepository.save(bridge);
        //tourRepository.save(testTour);
        //mediaLinkRepository.save(testML);

    }


}
