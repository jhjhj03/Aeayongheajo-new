package com.example.aeayongheajo;


import android.util.Base64;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthClient {
    public static void fetchAccessToken(String clientId, String clientSecret, Callback<TokenResponse> callback) {
        String credentials = clientId + ":" + clientSecret;
        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyAuthService service = retrofit.create(SpotifyAuthService.class);
        Call<TokenResponse> call = service.getAccessToken(basicAuth, "client_credentials");
        call.enqueue(callback);
    }
}