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
    //

    @Override
    public void run(String... args) {
        // HANDLE BRIDGE SETUP
        BridgeStatus bridge = new BridgeStatus(1L, "open");
        bridgeRepository.save(bridge);

        // DUMMY DATA


        Pin harbor = new Pin(null, 55.32073, 15.18601, "I 1684 besluttede Christian 5. at bygge et " +
                "fæstningsanlæg på øerne omkring den naturlige havn mellem Kirkeholmen og Bodholmen. <br>" +
                "Anthon Coucheron fik opgaven og blev den første kommandant. Fæstningen kom til at hedde \"Christiansø\", " +
                "og de to hovedøer skiftede navn til Christiansø (efter Christian 5.) og Frederiksø (efter Frederik 4.).<br> " +
                "Fæstningen bestod af to tårne, Store Tårn på Christiansø og Lille Tårn på Frederiksø, samt nogle bastioner og ringmure.<br> " +
                "Fæstningens andre bygninger blev opført omkring havnen. Fæstningen fungerede som militært anlæg indtil 1855.", "Christiansø Havn");

        Pin church = new Pin(null, 55.32132, 15.18703, "Christiansø Kirke er en kirke i " +
                "Christiansø Sogn. Kirken blev indrettet i 1821 i en tidligere våbensmedje. Indtil da, og siden 1685, " +
                "havde der været en kirke i den underste etage i det største tårn i fæstningen.\n" +
                "Christiansø Kirke blev ombygget og udvidet i 1852, og ved den lejlighed fik den et orgel, bygget af Frederik Hoffmann Ramus.\n" +
                "Kirken blev igen restaureret i 1928 i nyklassicistisk stil., ", "Kirke");

        Pin millersHouse = new Pin(null, 55.31933, 15.18896, "Møllerens hus, her bor mølleren", "Møllerens hus");


        Pin mill = new Pin(null, 55.31884, 15.189182, "Christiansø mølle", "Møllen");



        Tour walkToMillersHouse = new Tour(null, "Gå tur i eftermiddagssolen, til Møllerens hus");
        List<Pin> walkToMillersHousePinList = List.of(harbor, church, millersHouse, mill);
        walkToMillersHouse.setPins(walkToMillersHousePinList);

        harbor.setTours(List.of(walkToMillersHouse));
        church.setTours(List.of(walkToMillersHouse));
        millersHouse.setTours(List.of(walkToMillersHouse));
        mill.setTours(List.of(walkToMillersHouse));


        MediaLink harborLink = new MediaLink(null, "<iframe width=\"560\" height=\"315\" " +
                "src=\"https://www.youtube.com/embed/gIP3MCLzaiQ\" title=\"YouTube video player\" " +
                "frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" " +
                "allowfullscreen></iframe>", MediaType.VIDEO);

        harbor.setMediaLinks(List.of(harborLink));

        MediaLink arrivalLinkforMillersHouseTour = new MediaLink(null, "<iframe width=\"560\" height=\"315\" " +
                "src=\"https://www.youtube.com/embed/EDb6ydPKr40\" title=\"YouTube video player\" frameborder=\"0\" " +
                "allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" " +
                "allowfullscreen></iframe>", MediaType.VIDEO);

        walkToMillersHouse.setMediaLinks(List.of(arrivalLinkforMillersHouseTour));



        List<Pin> setUpPins = List.of(harbor, church, millersHouse);
        List<Tour> setUpTours = List.of(walkToMillersHouse);
        List<MediaLink> setUpLinks = List.of(harborLink, arrivalLinkforMillersHouseTour);



        pinRepository.save(harbor);
        pinRepository.save(church);
        pinRepository.save(millersHouse);
        pinRepository.save(mill);



        Pin frog = new Pin(null,55.319360,15.189781,"Frø lokation 1","Frø");
        Pin seal = new Pin(null,55.31895,15.19147 ,"Sæl lokation 1","Sæl");
        Pin fugl = new Pin(null,55.31765,15.18865,"Fugle lokation 1","Fugl");

        //Dyr
        pinRepository.save(fugl);
        pinRepository.save(seal);
        pinRepository.save(frog);

    }


}
