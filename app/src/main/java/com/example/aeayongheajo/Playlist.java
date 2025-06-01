package com.example.aeayongheajo;

public class Playlist {
    private final String id;
    private final String title;

    public Playlist(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
}
