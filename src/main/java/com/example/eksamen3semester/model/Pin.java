package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pins")
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_id")
    private Long pinId;

    @Column(name = "pos_x")
    private int posX;

    @Column(name = "pos_y")
    private int posY;

    private double latitude;
    private double longitude;
    private String description;

    @OneToMany(mappedBy = "pin")
    private List<PinTour> tours;


    @OneToMany(mappedBy = "pin")
    private List<PinMedia> mediaLinks;


    public Pin (){}

    public Pin(Long pinId, int posX, int posY, double latitude, double longitude, String description) {
        this.pinId = pinId;
        this.posX = posX;
        this.posY = posY;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public Pin(Long pinId, int posX, int posY, double latitude, double longitude, String description, List<PinTour> tours, List<PinMedia> mediaLinks) {
        this.pinId = pinId;
        this.posX = posX;
        this.posY = posY;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.tours = tours;
        this.mediaLinks = mediaLinks;
    }

    public Long getPinId() {
        return pinId;
    }

    public void setPinId(Long pinId) {
        this.pinId = pinId;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PinTour> getTours() {
        return tours;
    }

    public void setTours(List<PinTour> tours) {
        this.tours = tours;
    }

    public List<PinMedia> getMediaLinks() {
        return mediaLinks;
    }

    public void setMediaLinks(List<PinMedia> mediaLinks) {
        this.mediaLinks = mediaLinks;
    }
}
