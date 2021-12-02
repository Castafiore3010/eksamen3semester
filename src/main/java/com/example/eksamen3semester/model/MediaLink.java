package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "media_links")
public class MediaLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_link_id")
    private Long mediaLinkId;

    @Column(name = "media_link")
    private String mediaLink;

    @Column(name = "media_type")
    private MediaType type;

    @OneToMany(mappedBy = "mediaLink")
    private List<PinMedia> pins;

    @OneToMany(mappedBy = "mediaLink")
    private List<TourMedia> tours;

    public MediaLink(){}

    public MediaLink(Long mediaLinkId, String mediaLink, MediaType type) {
        this.mediaLinkId = mediaLinkId;
        this.mediaLink = mediaLink;
        this.type = type;
    }

    public MediaLink(Long mediaLinkId, String mediaLink, MediaType type, List<PinMedia> pins, List<TourMedia> tours) {
        this.mediaLinkId = mediaLinkId;
        this.mediaLink = mediaLink;
        this.type = type;
        this.pins = pins;
        this.tours = tours;
    }

    public Long getMediaLinkId() {
        return mediaLinkId;
    }

    public void setMediaLinkId(Long mediaLinkId) {
        this.mediaLinkId = mediaLinkId;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public List<PinMedia> getPins() {
        return pins;
    }

    public void setPins(List<PinMedia> pins) {
        this.pins = pins;
    }

    public List<TourMedia> getTours() {
        return tours;
    }

    public void setTours(List<TourMedia> tours) {
        this.tours = tours;
    }
}
