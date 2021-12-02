package com.example.eksamen3semester.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PinTourKey implements Serializable {
    @Column (name = "pin_id")
    Long pinId;

    @Column (name = "tour_id")
    Long tourId;


}
