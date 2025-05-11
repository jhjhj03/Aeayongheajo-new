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

    public void fetchRecommendations(String token, String genre, float energy, float valence, int limit, Callback<RecommendationResponse> callback) {
        String authHeader = "Bearer " + token;
        Call<RecommendationResponse> call = apiService.getRecommendations(authHeader, genre, energy, valence, limit);
        call.enqueue(callback);
    }
}