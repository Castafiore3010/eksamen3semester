package com.example.eksamen3semester.model;

public enum MediaType {
    AUDIO("audio"),
    PHOTO("photo"),
    VIDEO("video");

    private final String type;
    MediaType(String type) {
        this.type = type;
    }
}
