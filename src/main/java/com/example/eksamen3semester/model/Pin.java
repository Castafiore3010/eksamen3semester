package com.example.eksamen3semester.model;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

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
    @Column(length = 10000)
    private String description;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tour_pins", joinColumns = @JoinColumn(name = "pin_id"), inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<Tour> tours;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pin_media_links", joinColumns = @JoinColumn(name = "media_link_id"), inverseJoinColumns = @JoinColumn(name = "pin_id"))
    private List<MediaLink> mediaLinks;

    private String title;


    public Pin (){}

    public Pin(Long pinId, int posX, int posY, double latitude, double longitude, String description) {
        this.pinId = pinId;
        this.posX = posX;
        this.posY = posY;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public Pin(Long pinId, int posX, int posY, double latitude, double longitude, String description, List<Tour> tours, List<MediaLink> mediaLinks) {
        this.pinId = pinId;
        this.posX = posX;
        this.posY = posY;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.tours = tours;
        this.mediaLinks = mediaLinks;
    }

    public Pin(Long pinId, double latitude, double longitude, String description) {
        this.pinId = pinId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public Pin(Long pinId, double latitude, double longitude, String description, String title) {
        this.pinId = pinId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.title = title;
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

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<MediaLink> getMediaLinks() {
        return mediaLinks;
    }

    public void setMediaLinks(List<MediaLink> mediaLinks) {
        this.mediaLinks = mediaLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
