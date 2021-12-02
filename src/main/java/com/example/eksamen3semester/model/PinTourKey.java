package com.example.eksamen3semester.model;

import java.io.Serializable;


public class PinTourKey implements Serializable {
    private Long pinId;

    private Long tourId;


    public PinTourKey() {}

    public PinTourKey(Long pinId, Long tourId) {
        this.pinId = pinId;
        this.tourId = tourId;
    }
}
