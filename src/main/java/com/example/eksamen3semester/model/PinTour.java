package com.example.eksamen3semester.model;

import javax.persistence.*;

@Entity
@IdClass(PinTourKey.class)
@Table(name = "tour_pins")
public class PinTour {
    @Id
    @Column(name = "pin_id")
    private Long pinId;

    @Id
    @Column(name = "tour_id")
    private Long tourId;

    public PinTour() {}

    public PinTour(Long pinId, Long tourId) {
        this.pinId = pinId;
        this.tourId = tourId;
    }


    public Long getPinId() {
        return pinId;
    }

    public void setPinId(Long pinId) {
        this.pinId = pinId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

}
