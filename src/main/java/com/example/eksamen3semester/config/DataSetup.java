package com.example.eksamen3semester.config;

import com.example.eksamen3semester.model.*;
import com.example.eksamen3semester.repository.MediaLinkRepository;
import com.example.eksamen3semester.repository.PinMediaRepository;
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
    PinMediaRepository pinMediaRepository;

    public DataSetup(PinRepository pinRepository, TourRepository tourRepository, MediaLinkRepository mediaLinkRepository,
                     PinMediaRepository pinMediaRepository){
        this.pinRepository = pinRepository;
        this.tourRepository = tourRepository;
        this.mediaLinkRepository = mediaLinkRepository;
        this.pinMediaRepository = pinMediaRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("HEJ MED OS");
        //HEJ MARC, VI SLETTER DET HER OG LAVER SQL STATEMENTS ISTEDET, FOR JPA ER SKRALD OG VI GIDER IKKE FINDE UD AF
        // HVORDAN DET FUNGERER :) HILSEN DEN BEDRE TUTOR


        MediaLink testML = new MediaLink(null,"www.smileyface.dk", MediaType.VIDEO);
        mediaLinkRepository.save(testML);

        Pin testerPin = new Pin(null,400, 600, 22, 22, "JAMEN GODDAG DU");
        pinRepository.save(testerPin);

        PinMediaKey nøglen = new PinMediaKey(testerPin.getPinId(), testML.getMediaLinkId());
        PinMedia tester = new PinMedia(nøglen, testerPin, testML);




        pinMediaRepository.save(tester);
        //pinRepository.save(testPin);
        //tourRepository.save(testTour);
        //mediaLinkRepository.save(testML);

    }


}


