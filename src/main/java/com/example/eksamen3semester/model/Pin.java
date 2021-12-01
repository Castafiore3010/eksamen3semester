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

    @ManyToMany(mappedBy = "pins")
    private List<Tour> tours;

    @ManyToMany()
    @JoinTable(name = "pin_media_links", joinColumns = @JoinColumn(name = "media_link_id"), inverseJoinColumns = @JoinColumn(name = "pin_id"))
    private List<MediaLink> mediaLinks;


    public void setId(Long id) {
        this.pinId = id;
    }

    public Long getId() {
        return pinId;
    }
}
