package com.example.eksamen3semester.model;


import javax.persistence.*;


@Entity
public class TourMedia {
    @EmbeddedId
    TourMediaKey id;

    @ManyToOne
    @MapsId("tourId")
    @JoinColumn(name = "tour_id")
    Tour tour;

    @ManyToOne
    @MapsId("mediaLinkId")
    @JoinColumn(name = "media_link_id")
    MediaLink mediaLink;

    public TourMedia() {}

    public TourMedia(TourMediaKey id, Tour tour, MediaLink mediaLink) {
        this.id = id;
        this.tour = tour;
        this.mediaLink = mediaLink;
    }

    public TourMediaKey getId() {
        return id;
    }

    public void setId(TourMediaKey id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public MediaLink getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(MediaLink mediaLink) {
        this.mediaLink = mediaLink;
    }
}
