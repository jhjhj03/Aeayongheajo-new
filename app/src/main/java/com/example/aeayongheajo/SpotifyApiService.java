package com.example.aeayongheajo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SpotifyApiService {
    @GET("v1/recommendations")
    Call<RecommendationResponse> getRecommendations(
            @Header("Authorization") String authHeader,
            @Query("seed_genres") String genre,
            @Query("target_energy") float energy,
            @Query("target_valence") float valence,
            @Query("limit") int limit
    );
}
