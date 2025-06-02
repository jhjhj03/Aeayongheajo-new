package com.example.aeayongheajo.apicall;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PlaylistTracksResponse {
    @SerializedName("items")
    public List<Item> items;

    public static class Item {
        @SerializedName("track")
        public Track track;
    }

    public static class Track {
        @SerializedName("name")
        public String name;

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
