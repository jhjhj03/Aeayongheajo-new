package com.example.aeayongheajo;

public class Playlist {
    private int id;
    private String title;
    private String mood;

    public Playlist(int id, String title, String mood) {
        this.id = id;
        this.title = title;
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMood() {
        return mood;
    }
}
