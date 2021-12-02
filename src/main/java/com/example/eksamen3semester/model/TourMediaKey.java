package com.example.eksamen3semester.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TourMediaKey implements Serializable {
    @Column (name = "tour_id")
    Long tourId;

    @Column (name = "media_link_id")
    Long mediaLinkId;
}
