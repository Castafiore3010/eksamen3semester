package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long tourId;

    private String description;

    @ManyToMany()
    @JoinTable(name = "tour_pins", joinColumns = @JoinColumn(name = "pin_id"), inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<Pin> pins;

    @ManyToMany()
    @JoinTable(name = "tour_media_links", joinColumns = @JoinColumn(name = "media_link_id"), inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<MediaLink> mediaLinks;

    public void setTourId(Long id) {
        this.tourId = id;
    }

    public Long getTourId() {
        return tourId;
    }
}
