package com.example.eksamen3semester.model;

import org.thymeleaf.expression.Maps;

import javax.persistence.*;

@Entity
public class PinTour {

    @EmbeddedId
    PinTourKey id;

    @ManyToOne
    @MapsId("pinId")
    @JoinColumn(name = "pin_id")
    Pin pin;

    @ManyToOne
    @MapsId("tourId")
    @JoinColumn(name = "tour_id")
    Tour tour;

    public PinTour() {}

    public PinTour(PinTourKey id, Pin pin, Tour tour) {
        this.id = id;
        this.pin = pin;
        this.tour = tour;
    }

    public PinTourKey getId() {
        return id;
    }

    public void setId(PinTourKey id) {
        this.id = id;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

}
