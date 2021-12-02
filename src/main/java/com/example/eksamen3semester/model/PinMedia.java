package com.example.eksamen3semester.model;

import javax.persistence.*;


@Entity
public class PinMedia {

    @EmbeddedId
    PinMediaKey id;

    @ManyToOne
    @MapsId("pinId")
    @JoinColumn(name="pin_id")
    Pin pin;

    @ManyToOne
    @MapsId("mediaLinkId")
    @JoinColumn(name ="media_link_id")
    MediaLink mediaLink;



    public PinMedia() {}

    public PinMedia(PinMediaKey id, Pin pin, MediaLink mediaLink) {
        this.id = id;
        this.pin = pin;
        this.mediaLink = mediaLink;
    }

    public PinMediaKey getId() {
        return id;
    }

    public void setId(PinMediaKey id) {
        this.id = id;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public MediaLink getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(MediaLink mediaLink) {
        this.mediaLink = mediaLink;
    }
}
