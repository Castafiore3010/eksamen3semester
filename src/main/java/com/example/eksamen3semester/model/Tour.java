package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long tourId;

    private String description;

    @OneToMany(mappedBy = "tour")
    private List<PinTour> pins;


    @OneToMany(mappedBy = "tour")
    private List<TourMedia> mediaLinks;


    public Tour() {
    }

    public Tour(Long tourId, String description) {
        this.tourId = tourId;
        this.description = description;
    }

    public Tour(Long tourId, String description, List<PinTour> pins, List<MediaLink> mediaLinks) {
        this.tourId = tourId;
        this.description = description;
        this.pins = pins;
        //this.mediaLinks = mediaLinks;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PinTour> getPins() {
        return pins;
    }

    public void setPins(List<PinTour> pins) {
        this.pins = pins;
    }

    //public List<MediaLink> getMediaLinks() {
        //return mediaLinks;
    //}

    public void setMediaLinks(List<MediaLink> mediaLinks) {
        //this.mediaLinks = mediaLinks;
    }
}
