package com.example.aeayongheajo.apicall;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecommendationResponse {
    @SerializedName("tracks")
    public List<Track> tracks;

    public static class Track {
        public String name;
        public List<Artist> artists;
        public Album album;
    }

    public static class Artist {
        public String name;
    }

    public static class Album {
        public List<Image> images;
    }

    public static class Image {
        public String url;
    }
}