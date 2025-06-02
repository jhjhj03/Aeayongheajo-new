package com.example.aeayongheajo.apicall;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotifyAuthService {
    @FormUrlEncoded
    @POST("api/token")
    Call<TokenResponse> getAccessToken(
            @Header("Authorization") String authHeader,
            @Field("grant_type") String grantType
    );
}
