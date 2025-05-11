package com.example.aeayongheajo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecommendationResponse {
    @SerializedName("tracks")
    public List<Track> tracks;

    public static class Track {
        @SerializedName("name")
        public String name;

        @SerializedName("uri")
        public String uri;

        @SerializedName("artists")
        public List<Artist> artists;

        @SerializedName("album")
        public Album album;
    }

    public static class Artist {
        @SerializedName("name")
        public String name;
    }

    public static class Album {
        @SerializedName("images")
        public List<Image> images;
    }

    public static class Image {
        @SerializedName("url")
        public String url;
    }
}
