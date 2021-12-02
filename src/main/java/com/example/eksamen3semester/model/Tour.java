package com.example.eksamen3semester.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tour {

    public static class ShortPinList {
        Long pinId;
        String description;

        public ShortPinList(Long pinId, String description) {
            this.pinId = pinId;
            this.description = description;
        }

        public Long getPinId() {
            return pinId;
        }

        public void setPinId(Long pinId) {
            this.pinId = pinId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long tourId;

    private String description;

    @ManyToMany(mappedBy = "tours", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Pin> pins;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tour_media_links", joinColumns = @JoinColumn(name = "media_link_id"), inverseJoinColumns = @JoinColumn(name = "tour_id"))
    @JsonIgnore
    private List<MediaLink> mediaLinks;

    @Transient
    private List<ShortPinList> shortPinList;


    public Tour() {
    }

    public Tour(Long tourId, String description) {
        this.tourId = tourId;
        this.description = description;
    }

    public Tour(Long tourId, String description, List<Pin> pins, List<MediaLink> mediaLinks) {
        this.tourId = tourId;
        this.description = description;
        this.pins = pins;
        this.mediaLinks = mediaLinks;
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

    public List<Pin> getPins() {
        return pins;
    }

    public void setPins(List<Pin> pins) {
        this.pins = pins;
    }

    public List<MediaLink> getMediaLinks() {
        return mediaLinks;
    }

    public void setMediaLinks(List<MediaLink> mediaLinks) {
        this.mediaLinks = mediaLinks;
    }

    public List<ShortPinList> getShortPinList() {
        return shortPinList;
    }

    public void setShortPinList(List<ShortPinList> shortPinList) {
        this.shortPinList = shortPinList;
    }
}
