package com.example.eksamen3semester.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PinMediaKey implements Serializable {

    @Column (name="pin_id")
    Long pinId;

    @Column (name ="media_link_id")
    Long mediaLinkId;


    public PinMediaKey () {}
    public PinMediaKey(Long pinId, Long mediaLinkId) {
        this.pinId = pinId;
        this.mediaLinkId = mediaLinkId;
    }
}
