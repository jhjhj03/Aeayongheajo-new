package com.example.aeayongheajo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class SpotifyRepository {
    private static final String BASE_URL = "https://api.spotify.com/";
    private final SpotifyApiService apiService;

    public SpotifyRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(SpotifyApiService.class);
    }

    public void fetchPlaylistTracks(String token, String playlistId, Callback<PlaylistTracksResponse> callback) {
        String authHeader = "Bearer " + token;
        Call<PlaylistTracksResponse> call = apiService.getPlaylistTracks(authHeader, playlistId);
        call.enqueue(callback);
    }

}