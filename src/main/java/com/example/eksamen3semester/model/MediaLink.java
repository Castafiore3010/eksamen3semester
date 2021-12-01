package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "media_links")
public class MediaLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_link_id")
    private Long mediaLinkId;

    @Column(name = "media_link")
    private String mediaLink;

    @Column(name = "media_type")
    private MediaType type;

    @ManyToMany(mappedBy = "mediaLinks")
    private List<Pin> pins;

    @ManyToMany(mappedBy = "mediaLinks")
    private List<Pin> tours;

    public void setMediaLinkId(Long id) {
        this.mediaLinkId = id;
    }

    public Long getMediaLinkId() {
        return mediaLinkId;
    }
}
